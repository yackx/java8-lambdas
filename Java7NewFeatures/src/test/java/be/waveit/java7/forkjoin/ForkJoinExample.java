package be.waveit.java7.forkjoin;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class ForkJoinExample {
	static long execute(String path){
		int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors + " processor"
                + (processors != 1 ? "s are " : " is ")
                + "available");

        DirectorySizeTask task = new DirectorySizeTask(Paths.get(path));
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(task);
	}
}

class DirectorySizeTask extends RecursiveTask<Long>{
	private static final long serialVersionUID = -5035850745336856440L;
	private Path rootPath;

	DirectorySizeTask(Path rootPath) {
		super();
		this.rootPath = rootPath;
	}

	@Override
	protected Long compute() {
		Long size = 0L;
		List<Path> files = new ArrayList<>();
		List<Path> directories = new ArrayList<>();

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(rootPath)) {
			for (Path entry : stream) {
				if (Files.isDirectory(entry)) {
					directories.add(entry);
				} else {
					files.add(entry);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		size += computeFiles(files);

		List<DirectorySizeTask> subTasks = createSubTasks(directories);

		invokeAll(subTasks);

		for (DirectorySizeTask task : subTasks){
			size += task.join();
		}

//		System.out.println(Thread.currentThread().getName() + " - Treating directory : " + rootPath + " - size = " + size + " bytes.");
		return size;
	}

	private List<DirectorySizeTask> createSubTasks(List<Path> directories){
		List<DirectorySizeTask> tasks = new ArrayList<>();
		for (Path path : directories) {
			tasks.add(new DirectorySizeTask(path));
		}
		return tasks;
	}

	private Long computeFiles(List<Path> fileList) {
		long size = 0;
		for (Path path : fileList){
			try {
				size += Files.size(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return size;
	}
}
