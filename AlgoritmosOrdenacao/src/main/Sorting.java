package main;

public class Sorting {

	static Long iterations = null;
	
	// ----------------------------------------------------------------------------------
	/**
	 * Bubble Sort padrão sem melhorias.
	 * O(n^2)
	 * 
	 * @param array
	 */
	public static void bubbleSort(Integer[] array) {
		iterations = (long)0;
		int n = array.length,i=0,j=0;
		Integer aux;
		for(j=0; j<(n-1); j++) {
			for(i=0; i<n-j-1; i++) {
				iterations++;
				if(array[i] > array[i+1]) {
					aux = array[i];
					array[i] = array[i+1];
					array[i+1]= aux;
				}
			}
		}
		
		return;
	}

	// ----------------------------------------------------------------------------------
	/**
	 * Bubble Sort melhorado.
	 * O(n^2) ou O(n)
	 * 
	 * @param array
	 */
	public static void bubbleSortPlus(Integer[] array) {
		iterations = (long)0;
		boolean troca = true;
		int n = array.length, i=0, j=0;
		Integer aux;
		for(j=0; (j<(n-1)) && troca; j++) {
			troca = false;
			for(i=0; i<n-j-1; i++) {
				iterations++;
				if(array[i] > array[i+1]) {
					troca = true;
					aux = array[i];
					array[i] = array[i+1];
					array[i+1]= aux;
				}
			}
		}
		
		return;
	}
	
	// ----------------------------------------------------------------------------------

	/**
	 * Troca os elementos de um array 
	 * 
	 * @param X
	 * @param i
	 * @param j
	 */
    public static void troca(Integer[] X, int i, int j) {
    	Integer aux = X[i];
    	X[i] = X[j];
    	X[j] = aux;
    }
	
	// ----------------------------------------------------------------------------------
	/**
	 * Quick Sort com Pivô no início da partição
	 * 
	 * @param array
	 */
	public static void quickSortInicio(Integer[] array) {
		iterations = (long)0;
		quickSortInicioRec(array, 0, array.length-1);
		return;
	}
	
	/**
	 * Metodo que faz a recursão do
	 * Quick Sort com Pivô no início da partição
	 * 
	 * @param X
	 * @param p
	 * @param r
	 */
	public static void quickSortInicioRec(Integer[] X, int p, int r) {
		iterations++;
		if (p<r) {
			int q = quickSortInicioParticao(X, p, r);
			quickSortInicioRec(X, p, q-1);
			quickSortInicioRec(X, q+1, r);
		}
	}
	
	/**
	 * Define as proximas partições e faz as trocas.
	 * 
	 * @param X
	 * @param p
	 * @param r
	 * @return
	 */
	public static int quickSortInicioParticao(Integer[] X, int p, int r) {
		int pivo = X[p], up = r, down = p+1;
		while(down <= up) {
			iterations++;
//			System.out.println("PIVO: "+p+" DOWN: "+down+" UP: "+up);
			if(X[down] <= pivo) {
				down++;
			} else if (pivo < X[up]) {
				up--;
			} else {
				troca(X,up,down);
				up--;
				down++;
			}
		}
		X[p] = X[up];
		X[up] = pivo;
		return up;
	}
	
	// ----------------------------------------------------------------------------------
	
	
	/**
	 * Retorna o index central através da mediana
	 * de 3 elementos, left, right e (left+right)/2
	 * 
	 * Já ordena os 3 elementos em tempo constante O(1)
	 * 
	 * @param a
	 * @param b
	 */
	public static int medianaDe3(Integer[] X, int left, int right) {
	    int center = (left + right) / 2;
	    if (X[left] > X[center]) troca(X, left, center);
	    if (X[left] > X[right]) troca(X, left, right);
	    if (X[center] > X[right]) troca(X, center, right);

	    return center;
	}

	/**
	 * Quick Sort com escolhe de pivo central
	 * através da mediana de 3 elementos (extremos e centro)
	 * 
	 * https://pt.wikipedia.org/wiki/Quicksort
	 * @param array
	 */
	public static void quickSortCentro(Integer[] array) {
		iterations = (long)0;
		quickSortCentroRec(array, 0, array.length-1);
		return;
	}
	
	/**
	 * Recursividade do quick sort central
	 * 
	 * @param X
	 * @param ini
	 * @param fim
	 */
	public static void quickSortCentroRec(Integer[] X, int ini, int fim) {
		int i = ini;
		int j = fim;
		int pivo = X[medianaDe3(X, ini, fim)];
		
		while(i <= j) {
			while(X[i] < pivo) {
				iterations++;
				i++;
			}
			while(X[j] > pivo) {
				iterations++;
				j--;
			}
			if(i <= j) {
				troca(X,i,j);
				i++; j--;				
			}
		}
		
		if(ini<j) quickSortCentroRec(X,ini,j);
		if(i<fim) quickSortCentroRec(X,i,fim);
	}
	
	
	// ----------------------------------------------------------------------------------
	
	/**
	 * InsertionSort
	 * http://www.guj.com.br/t/insertion-sort/91452
	 * 
	 * @param X
	 */
	public static void insertionSort(Integer[] array) {
		iterations = (long)0;
		int n = array.length;
		for (int i = 0; i < n; i++)  {
		    int a = array[i];  
		    for (int j = i - 1; (j >= 0 && array[j] > a); j--)   { 
		        array[j + 1] = array[j];  
		        array[j] = a;  
		    }                         
		}

	}
	
	// ----------------------------------------------------------------------------------
	
	/**
	 * Shell Sort (Pulo 3)
	 * http://www.guj.com.br/t/shellsort/65683/3
	 * 
	 * @param X
	 */
	public static void shellSort(Integer[] array, int gap) {
		iterations = (long)0;
		int i,j,pulo=1,valor;
		int n = array.length;
		// Define a ultima posição do pulo
		do {
			pulo = gap*pulo+1;
		} while (pulo<n);
		
		do {
			pulo = pulo/gap;
			for (i=pulo; i<n; i++) {
				valor = array[i];
				j = i-pulo;
				iterations++;
				while (j >= 0 && valor < array[j]) {
					iterations++;
					array [j+pulo] = array[j];
					j = j - pulo;
				}
				array [j+pulo] = valor;
			}
		} while (pulo>1);
	}

	// ----------------------------------------------------------------------------------
	
	/**
	 * Selection Sort
	 * 
	 * @param X
	 */
	public static void selectionSort(Integer[] array) {
		iterations = (long)0;
        int n = array.length;
        for (int i = 0; i < n-1; i++) {
            int menor = i;
            for (int j = i+1; j < n; j++) {
				iterations++;
                if (array[j] < array[menor])
                    menor = j;
            }
            troca(array,menor,i);
        }
	}
	
	// ----------------------------------------------------------------------------------
	
	/**
	 * Heap Sort
	 * https://www.geeksforgeeks.org/heap-sort/
	 * 
	 * @param X
	 */
	public static void heapSort(Integer[] array) {
		iterations = (long)0;
		
        int n = array.length;
        
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
        	max_heapify(array, n, i);
        }
 
        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--) {
            // Move current root to end
        	troca(array,0,i);
 
            // call max heapify on the reduced heap
        	max_heapify(array, i, 0);
        }
		
	}
	
	// To heapify a subtree rooted with node i which is
	// an index in arr[]. n is size of heap
	public static void max_heapify(Integer[] arr, int n, int i) {
		iterations++;
		int largest = i;  // Initialize largest as root
		int l = 2*i + 1;  // left = 2*i + 1
		int r = 2*i + 2;  // right = 2*i + 2

		// If left child is larger than root
		if (l < n && arr[l] > arr[largest])
			largest = l;

		// If right child is larger than largest so far
		if (r < n && arr[r] > arr[largest])
			largest = r;

		// If largest is not root
		if (largest != i)
		{
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;

			// Recursively heapify the affected sub-tree
			max_heapify(arr, n, largest);
		}
	}


	// ----------------------------------------------------------------------------------
	
	/**
	 * Merge Sort
	 * https://www.geeksforgeeks.org/merge-sort/
	 * 
	 * @param X
	 */
	public static void mergeSort(Integer[] array) {
		iterations = (long)0;
        mergeSortRec(array, 0, array.length-1);
	}
	
	/**
	 * MergeSort recursividade
	 * 
	 * @param arr
	 * @param left
	 * @param right
	 */
	public static void mergeSortRec(Integer[] arr, int left, int right) {
        if (left < right) {
        	iterations++;
            // Find the middle point
            int m = (left+right)/2;
 
            // Sort first and second halves
            mergeSortRec(arr, left, m);
            mergeSortRec(arr , m+1, right);
 
            // Merge the sorted halves
            mergeSortMerge(arr, left, m, right);
        }
	}
	
	/**
	 * Merges two subarrays of arr[].
	 * First subarray is arr[left..middle]
	 * Second subarray is arr[middle+1..right]
	 * 
	 * @param arr
	 * @param left
	 * @param middle
	 * @param right
	 */
    public static void mergeSortMerge(Integer[] arr, int left, int middle, int right)
    {
        // Find sizes of two subarrays to be merged
        int n1 = middle - left + 1;
        int n2 = right - middle;
 
        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];
 
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i) {
        	iterations++;
            L[i] = arr[left + i];
        }
        for (int j=0; j<n2; ++j) {
        	iterations++;
            R[j] = arr[middle + 1+ j];
        }
 
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarry array
        int k = left;
        while (i < n1 && j < n2) {
        	iterations++;
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
        	iterations++;

            arr[k] = L[i];
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
        	iterations++;

            arr[k] = R[j];
            j++;
            k++;
        }
    }

	
}