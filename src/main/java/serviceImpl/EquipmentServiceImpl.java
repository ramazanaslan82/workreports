package serviceImpl;

import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Equipment;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;

import service.EquipmentService;

public class EquipmentServiceImpl implements EquipmentService {
	private static List<String> names = new ArrayList<String>();

	public EquipmentServiceImpl() {
		super();
		buildEquipments();
	}

	private void buildEquipments() {
		names.clear();
		List<Equipment> equipments = getAllEquipments();
		CollectionUtils.forAllDo(equipments, new Closure() {
			public void execute(Object arg0) {
				Equipment equipment = (Equipment) arg0;
				names.add(equipment.getName().toLowerCase());
			}
		});
	}

	private List<Equipment> getAllEquipments() {
		List<Equipment> equipments = new ArrayList<Equipment>();
		equipments.add(new Equipment("NEFELOMETRE"));
		equipments.add(new Equipment("LUXMETRE"));
		equipments.add(new Equipment("SVAN 958"));
		equipments.add(new Equipment("CIRRUS MRZ"));
		equipments.add(new Equipment("KIMO"));
		equipments.add(new Equipment("QUEST"));
		equipments.add(new Equipment("OKTOVA"));
		equipments.add(new Equipment("GILIAN"));
		equipments.add(new Equipment("PDR"));
		equipments.add(new Equipment("CIRRUS"));
		equipments.add(new Equipment("01 KODLU TECORA"));
		equipments.add(new Equipment("15 KODLU TECORA"));
		equipments.add(new Equipment("203 KODLU TECORA"));
		equipments.add(new Equipment("204 KODLU TECORA"));
		equipments.add(new Equipment("203- 15 kodlu tecora"));
		equipments.add(new Equipment("TES MRZ"));
		equipments.add(new Equipment("KITAGAWA"));
		equipments.add(new Equipment("85 KODLU MADUR"));
		equipments.add(new Equipment("130 KODLU GILIAN"));
		equipments.add(new Equipment("madur"));
		equipments.add(new Equipment("dds"));
		equipments.add(new Equipment("sv 102"));
		equipments.add(new Equipment("flowtest"));
		equipments.add(new Equipment("signal"));
		equipments.add(new Equipment("henan"));
		equipments.add(new Equipment("svan 955"));
		equipments.add(new Equipment("sato"));
		return equipments;
	}

	public List<String> getEquipmentNames() {
		return names;
	}
}
