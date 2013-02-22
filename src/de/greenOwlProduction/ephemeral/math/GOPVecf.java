package de.greenOwlProduction.ephemeral.math;

/*
 * Copyright 2013, Sebastian Kreisel. All rights reserved.
 * If you intend to use, modify or redistribute this file contact kreisel.sebastian@gmail.com
 */

public interface GOPVecf {

	public float getN(int index);
	public void setN(int index, float value);

	public float[] toArray();

}
