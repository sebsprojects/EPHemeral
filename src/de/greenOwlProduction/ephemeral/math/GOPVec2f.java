package de.greenOwlProduction.ephemeral.math;

/*
 * Copyright 2013, Sebastian Kreisel. All rights reserved.
 * If you intend to use, modify or redistribute this file contact kreisel.sebastian@gmail.com
 */

public class GOPVec2f implements GOPVecf {

	protected float x, y;

	public GOPVec2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public GOPVec2f() {
		x = 0;
		y = 0;
	}

	@Override
	public float getN(int index) {
		switch (index) {
			case 0:
				return x;
			case 1:
				return y;
			default:
				return Float.NaN;
		}
	}

	@Override
	public void setN(int index, float value) {
		switch (index) {
			case 0:
				x = value;
				break;
			case 1:
				y = value;
				break;
		}
	}

	@Override
	public float[] toArray() {
		return new float[] { x, y };
	}

	public GOPVec2f addVec2f(GOPVec2f vec) {
		x += vec.x;
		y += vec.y;
		return this;
	}

	public GOPVec2f addVec2f(float vx, float vy) {
		x += vx;
		y += vy;
		return this;
	}

	public GOPVec2f subVec2f(GOPVec2f vec) {
		x -= vec.x;
		y -= vec.y;
		return this;
	}

	public GOPVec2f subVec2f(float vx, float vy) {
		x -= vx;
		y -= vy;
		return this;
	}

	public GOPVec2f mulVec2f(GOPVec2f vec) {
		x *= vec.x;
		y *= vec.y;
		return this;
	}

	public GOPVec2f mulVec2f(float vx, float vy) {
		x *= vx;
		y *= vy;
		return this;
	}

	public GOPVec2f mulScalar(float scalar) {
		x *= scalar;
		y *= scalar;
		return this;
	}

	public float dot(GOPVec2f vec) {
		return x * vec.x + y * vec.y;
	}

	public float dot(float vx, float vy) {
		return x * vx + y * vy;
	}

	public float length() {
		return (float) Math.hypot(x, y);
	}

	public GOPVec2f normalize() {
		float oldLength = length();
		x /= oldLength;
		y /= oldLength;
		return this;
	}

	public GOPVec2f negate() {
		x = -x;
		y = -y;
		return this;
	}

	public GOPVec2f toLength(float newLength) {
		return this.normalize().mulScalar(newLength);
	}

	public float angle(GOPVec2f vec) {
		return (float) Math.acos(dot(vec) / (length() * vec.length()));
	}

	public float angle(float vx, float vy) {
		return (float) Math.acos(dot(vx, vy) / length() * length(vx, vy));
	}

	public float distance(GOPVec2f vec) {
		return length(x - vec.x, y - vec.y);
	}

	public float distance(float vx, float vy) {
		return length(x - vx, y - vy);
	}

	public float distance(GOPVec2f vec1, GOPVec2f vec2) {
		return length((vec1.x - x) - ((vec1.x - x) * (vec2.x / vec2.length())) * (vec2.x / vec2.length()),
				(vec1.y - y) - ((vec1.y - y) * (vec2.y / vec2.length())) * (vec2.y / vec2.length()));
	}

	public float distance(float a1, float a2, float b1, float b2) {
		return length((a1 - x) - ((a1 - x) * (b1 / length(b1, b2))) * (b1 / length(b1, b2)),
				(a2 - y) - ((a2 - y) * (b2 / length(b1, b2))) * (b2 / length(b1, b2)));
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public GOPVec2f setX(float x) {
		this.x = x;
		return this;
	}

	public GOPVec2f setY(float y) {
		this.y = y;
		return this;
	}

	public GOPVec2f setXY(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public GOPVec2f copy() {
		return new GOPVec2f(x, y);
	}

	public String toString() {
		return "(" + x + "  " + y + ")";
	}

	public static GOPVec2f addVec2f(GOPVec2f v1, GOPVec2f v2) {
		return new GOPVec2f(v1.x + v2.x, v1.y + v2.y);
	}

	public static GOPVec2f subVec2f(GOPVec2f v1, GOPVec2f v2) {
		return new GOPVec2f(v1.x - v2.x, v1.y - v2.y);
	}

	public static GOPVec2f mulVec2f(GOPVec2f v1, GOPVec2f v2) {
		return new GOPVec2f(v1.x * v2.x, v1.y * v2.y);
	}

	public static GOPVec2f mulScalar(GOPVec2f vec, float scalar) {
		return new GOPVec2f(vec.x * scalar, vec.y * scalar);
	}

	public static GOPVec2f normalize(GOPVec2f vec) {
		return new GOPVec2f(vec.x, vec.y).normalize();
	}

	public static float length(float vx, float vy) {
		return (float) Math.hypot(vx, vy);
	}

}