package com.luciano.bowlinggame.service;

import java.util.List;
import java.util.Map;

import com.luciano.bowlinggame.model.Roll;

public interface FileService {

	Map<String, List<Roll>> readFile(String path);

}
