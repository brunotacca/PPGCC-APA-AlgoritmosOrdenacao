package main;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Main {

	static long timeStarted;
	static long timeEstimatedTotal;
	static Calendar dateStarted;
	static Calendar dateEnded;
	
	static int timesToExecute = 5;

	
	/**
	0	BUBBLE("Bubble Sort"),
	1	BUBBLE_PLUS("Bubble Sort +"),
	2	QUICK_BEGIN("Quick Sort Inicio"),
	3	QUICK_CENTER("Quick Sort Centro"),
	4	INSERTION("Insertion Sort"),
	5	SHELL("Shell Sort"),
	6	SELECTION("Selection Sort"),
	7	HEAP("Heap Sort"),
	8	MERGE("Merge Sort");
	 */
	static int sortOption = 4;
	
//	static int arraySize =   1000;
//	static int arraySize =   5000;
//	static int arraySize =  10000;
//	static int arraySize =  25000;
//	static int arraySize =  50000;
	static int arraySize = 100000;
	
	static int arrayOrder = 0;
	static boolean printArray = false;
	
	public static void main(String[] args) {

		Integer[] arrayToOrder = new Integer[arraySize];
		
		arrayToOrder = orderedSampleRandomNumbersWithoutRepetition(0,arraySize*2,arraySize);
		
		int executions = timesToExecute;
		for(int i=0;i<3;i++) {
			arrayOrder = i;
			dateStarted = Calendar.getInstance();
			List<Integer> lista = Arrays.asList(arrayToOrder);
			
			timeEstimatedTotal = 0l;
			executions = timesToExecute;
			while(executions-->0) {
				switch (arrayOrder) {
					case 0:
						Collections.shuffle(lista);
						break;
					case 1:
						Collections.sort(lista);
						break;
					case 2:
						Collections.sort(lista);
						Collections.reverse(lista);
						break;
					default:
						break;
				}
				
				arrayToOrder = (Integer[]) lista.toArray();
				
				if(printArray) printArray(arrayToOrder,"A:");
				
				timeStarted = System.nanoTime();
				
				switch (sortOption) {
					case 0:
						Sorting.bubbleSort(arrayToOrder);
						break;
					case 1:
						Sorting.bubbleSortPlus(arrayToOrder);
						break;
					case 2:
						Sorting.quickSortInicio(arrayToOrder);
						break;
					case 3:
						Sorting.quickSortCentro(arrayToOrder);
						break;
					case 4:
						Sorting.insertionSort(arrayToOrder);
						break;
					case 5:
						Sorting.shellSort(arrayToOrder, 3);
						break;
					case 6:
						Sorting.selectionSort(arrayToOrder);
						break;
					case 7:
						Sorting.heapSort(arrayToOrder);
						break;
					case 8:
						Sorting.mergeSort(arrayToOrder);
						break;
					default:
						break;
				}
				
				timeEstimatedTotal += System.nanoTime()-timeStarted;
//				System.out.println(i+" timeEstimatedTotal: "+timeEstimatedTotal);
				if(printArray) printArray(arrayToOrder,"B:");
			}
			
			
			dateEnded = Calendar.getInstance();
			printResults();
		}
	}
	
	public static void printArray(Integer[] array, String prefix) {
		System.out.print(prefix+" [");
		for(int z=0; z<array.length; z++)
			System.out.print(String.format("%02d", array[z])+" ");
		System.out.println("] "+isSorted(array));
	}
	
	public static boolean isSorted(Integer[] a)
	{  
	    for ( int i = 0; i < a.length - 1 ; i++ ) {
	        if ( a[i] > a[i+1] )
	          return false;
	    }
	    return true;
	}
	
	public static String stringArray(Integer[] array) {
		String str = "[";
		for(int z=0; z<array.length; z++)
			str += String.format("%02d", array[z])+" ";
		str += "]";
		return str;
	}
	
	public static void printResults() {
		System.out.println("---------------------------------------------");
		System.out.println("Algorithim; "+SortOption.values()[sortOption].getDescricao()+" "+timesToExecute+"x times");
		System.out.println("Entry; "+arraySize+" "+(arrayOrder==0?"Aleatorio":(arrayOrder==1?"Crescente":"Decrescente")));
//		System.out.println("A0: "+timeEstimatedTotal);
		BigDecimal time = nanosecondsToSeconds(timeEstimatedTotal);
//		System.out.println("A: "+time);
		BigDecimal avg = time.divide(new BigDecimal(timesToExecute));
//		System.out.println("B: "+avg);
		String strTime = String.valueOf(avg);
//		System.out.println("C: "+strTime);
		strTime = strTime.substring(0, strTime.lastIndexOf('.')+4);
		strTime = strTime.replace('.', ',');
		System.out.println("Avg. Time(ms); "+strTime);
		System.out.println("Started at; "+formatarData(null,dateStarted.getTime()));
		System.out.println("Ended at; "+formatarData(null,dateEnded.getTime()));
		if(Sorting.iterations!=null) System.out.println("Iterations; "+Sorting.iterations);
	}

	/**
	 * Formata uma data
	 * 
	 * @param padrao
	 * @param data
	 * @return
	 */
	public static String formatarData(String padrao, Date data) {
        String result = null;
        if(data==null) return "dd/MM/yyyy HH:mm:ss.S";
        if(padrao==null) padrao = "dd/MM/yyyy HH:mm:ss.S";
        
        try {
            result = new SimpleDateFormat(padrao).format(data);
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return result;
	}
	
	/**
	 * Converte nanosegundso para segundos
	 * 
	 * @param nano
	 * @return
	 */
	public static BigDecimal nanosecondsToSeconds(long nano) {
		BigDecimal bigd = new BigDecimal(nano);
		return bigd.multiply(new BigDecimal(Math.pow(10,-6)));
	}
	
	/**
	 * Gera um array de numeros aleatorios sem repetição.
	 * <count> Números variando de <start> até <end>
	 * 
	 * Retirado de: https://stackoverflow.com/questions/16000196/java-generating-non-repeating-random-numbers
	 * 
	 * @param start
	 * @param end
	 * @param count
	 * @return
	 */
	public static Integer[] orderedSampleRandomNumbersWithoutRepetition(int start, int end, int count) {
	    Random rng = new Random();

	    Integer[] result = new Integer[count];
	    int cur = 0;
	    int remaining = end - start;
	    for (int i = start; i < end && count > 0; i++) {
	        double probability = rng.nextDouble();
	        if (probability < ((double) count) / (double) remaining) {
	            count--;
	            result[cur++] = i;
	        }
	        remaining--;
	    }
	    return result;
	}

	
}
