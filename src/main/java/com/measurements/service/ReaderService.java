package com.measurements.service;

import java.util.List;

import com.measurements.model.WorkLog;

public interface ReaderService {
	List<WorkLog> readFile();
}
