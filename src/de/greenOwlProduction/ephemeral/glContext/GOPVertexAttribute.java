package de.greenOwlProduction.ephemeral.glContext;

public class GOPVertexAttribute {

	protected int index, size, type, stride, offset;
	protected boolean normalized;
	protected String name;

	public GOPVertexAttribute(int index, int size, int type, int stride, int offset, boolean normalized, String name) {
		this.index = index;
		this.size = size;
		this.type = type;
		this.stride = stride;
		this.offset = offset;
		this.normalized = normalized;
		this.name = name;
	}

	public GOPVertexAttribute(int index, int size, int stride, int offset, String name) {
		this(index, size, GOPRenderUtils.TYPE_FLOAT, stride, offset, false, name);
	}

}
