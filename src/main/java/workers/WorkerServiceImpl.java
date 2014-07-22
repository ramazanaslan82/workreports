package workers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;

public class WorkerServiceImpl implements WorkerService {

	private static List<String> names = new ArrayList<String>();

	public WorkerServiceImpl() {
		super();
		buildWorkers();
	}

	private void buildWorkers() {
		List<Worker> workers = getAllWorkers();
		CollectionUtils.forAllDo(workers, new Closure() {
			public void execute(Object arg0) {
				Worker worker = (Worker) arg0;
				names.add(worker.getName());
			}
		});
	}

	private List<Worker> getAllWorkers() {
		List<Worker> workers = new ArrayList<Worker>();
		workers.add(new Worker("Enver"));
		workers.add(new Worker("İlker"));
		workers.add(new Worker("Yaşar"));
		workers.add(new Worker("Yusuf"));
		workers.add(new Worker("Samet"));
		workers.add(new Worker("Zahit"));
		workers.add(new Worker("Coşkun"));
		workers.add(new Worker("Bülent"));
		workers.add(new Worker("Kadir"));
		workers.add(new Worker("Ali"));
		workers.add(new Worker("Gökhan"));
		workers.add(new Worker("Cevdet"));
		workers.add(new Worker("Alper"));
		workers.add(new Worker("Figen"));
		return workers;
	}

	public List<String> getWorkerNames() {
		return names;
	}

}
