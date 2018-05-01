package main;

public enum SortOption {

	BUBBLE("Bubble Sort"),
	BUBBLE_PLUS("Bubble Sort +"),
	QUICK_BEGIN("Quick Sort Inicio"),
	QUICK_CENTER("Quick Sort Centro"),
	INSERTION("Insertion Sort"),
	SHELL("Shell Sort"),
	SELECTION("Selection Sort"),
	HEAP("Heap Sort"),
	MERGE("Merge Sort");
	
	private String descricao;
	private SortOption(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	
	
}
