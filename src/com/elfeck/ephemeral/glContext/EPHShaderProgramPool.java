/*
 * Copyright 2013, Sebastian Kreisel. All rights reserved.
 * If you intend to use, modify or redistribute this file contact kreisel.sebastian@gmail.com
 */

package com.elfeck.ephemeral.glContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.elfeck.ephemeral.glContext.uniform.EPHUniformLookup;



public class EPHShaderProgramPool {

	private Map<String, EPHShaderProgram> programs;

	public EPHShaderProgramPool(String parentPath) {
		programs = new HashMap<String, EPHShaderProgram>();
		initShaderProgramPool(parentPath);
	}

	private void initShaderProgramPool(String parentPath) {
		Map<String, String[]> shaderSrcPairs = new HashMap<String, String[]>();
		loadShaderFiles(new File(parentPath), shaderSrcPairs);
		for (String key : shaderSrcPairs.keySet()) {
			programs.put(key, new EPHShaderProgram(shaderSrcPairs.get(key)[0], shaderSrcPairs.get(key)[1], uniformStringToUtb(shaderSrcPairs.get(key)[2])));
		}
	}

	private void loadShaderFiles(File folder, Map<String, String[]> shaderSrcPairs) {
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				loadShaderFiles(fileEntry, shaderSrcPairs);
			} else {
				String[] name = fileEntry.getName().split("_");
				if (name[1].equals("vert.glsl")) {
					if (shaderSrcPairs.containsKey(name[0])) {
						shaderSrcPairs.get(name[0])[0] = loadShaderSource(fileEntry);
					} else {
						shaderSrcPairs.put(name[0], new String[] { loadShaderSource(fileEntry), null, null });
					}
					shaderSrcPairs.get(name[0])[2] = extractUniforms(shaderSrcPairs.get(name[0])[0]);
				} else {
					if (name[1].equals("frag.glsl")) {
						if (shaderSrcPairs.containsKey(name[0])) {
							shaderSrcPairs.get(name[0])[1] = loadShaderSource(fileEntry);
						} else {
							shaderSrcPairs.put(name[0], new String[] { null, loadShaderSource(fileEntry), null });
						}
					}
				}
			}
		}
	}

	private String extractUniforms(String src) {
		String result = "";
		String[] rawTokens = src.split(";");
		for (String s : rawTokens) {
			s = s.replaceAll("\n", "");
			if (s.startsWith("uniform")) {
				String[] lineTokens = s.split(" ");
				result += lineTokens[1] + "#" + lineTokens[2] + "%";
			}
		}
		return result;
	}

	private EPHShaderUniformCollection uniformStringToUtb(String uniformString) {
		String[] uniforms = uniformString.split("%");
		EPHShaderUniformCollection utb = new EPHShaderUniformCollection();
		for (String s : uniforms) {
			String[] cut = s.split("#");
			if (cut[0].equals("float") || cut[0].startsWith("vec")) utb.addUniformLookup(new EPHUniformLookup(cut[1]));
			if (cut[0].startsWith("mat")) utb.addUniformLookup(new EPHUniformLookup(cut[1]));
		}
		return utb;
	}

	private String loadShaderSource(File file) {
		StringBuilder source = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				source.append(line).append('\n');
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return source.toString();
	}

	protected void glInit() {
		for (String key : programs.keySet()) {
			programs.get(key).glCompileShaderSrc();
		}
	}

	protected EPHShaderProgram getShaderProgram(String key) {
		return programs.get(key);
	}

	public void glDisposeShaderPrograms() {
		for (String key : programs.keySet()) {
			programs.get(key).glDispose();
		}
	}

}
