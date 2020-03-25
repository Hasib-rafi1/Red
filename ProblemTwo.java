import java.util.*;

class IntervalClass{
	int start, end; 

	public IntervalClass(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

public class ProblemTwo {
	static Vector<Integer> []g; 
	  
	static boolean checkTrue(boolean []box) {
		for(boolean value: box){
			  if(!value){ return false;}
			}
			return true;
	}
	static void Dominant(ArrayList<ArrayList<Integer>> posibalities, int n) 
	{ 
		Iterator<ArrayList<Integer>> iter = posibalities.iterator();  
		int count = 0;
		while (iter.hasNext()) {
			ArrayList<Integer> set = iter.next();
			Iterator<Integer> iterSet = set.iterator();
			boolean []box = new boolean[n];
			for(int i=0;i<n;i++){
				box[i]=false;
			}
			while(iterSet.hasNext()) {
				int value = iterSet.next();
				box[value] =true;
				for(int i=0; i< g[value].size(); i++) {
					box[g[value].get(i)] =true;

				}

			}
			if(checkTrue(box)) {
				count ++;
			}
		}
		System.out.print(count);
		
	} 
	  
	
    static ArrayList<ArrayList<Integer>> getSubsets(int[] intArray) 
    { 
        int n = intArray.length; 
        ArrayList<ArrayList<Integer>> posiblaity = new ArrayList<ArrayList<Integer>>();
        
        for (int i = 0; i < (1<<n); i++) 
        {  
        	ArrayList<Integer> set = new ArrayList<Integer>();
            // Print current subset 
            for (int j = 0; j < n; j++) {
  
                if ((i & (1 << j)) > 0) 
                	set.add(intArray[j]);
            }
            if(!set.isEmpty()) {
            	posiblaity.add(set);
            }
        } 
        
        return posiblaity;
    } 
 
	public static void main(String[] args) 
	{ 
		Scanner myInput = new Scanner(System.in);
		int n  = myInput.nextInt();
		ArrayList<IntervalClass> interval = new ArrayList<IntervalClass>();
		
		int[] intArray = new int[n];
		for(int i=0; i <=n;i++) {
			String path = myInput.nextLine();
			
			if(i!=0) {
				String[] a = path.split(" ");
				IntervalClass individualIntarval = new IntervalClass(Integer.parseInt(a[0]),Integer.parseInt(a[1]));
				interval.add(individualIntarval);	
			}
		}
		g = new Vector[n]; 
		for (int i = 0; i < n; i++) 
	        g[i] = new Vector<Integer>(); 
		
		for(int i = 0 ; i< interval.size();i++) {
			intArray[i]=i;
			for(int j= i+1 ; j<interval.size(); j++) {
				if(Math.max(interval.get(i).start, interval.get(j).start)<= Math.min(interval.get(i).end, interval.get(j).end)) {
					
					g[i].add(j);
					g[j].add(i);
				}
			}
		}
		ArrayList<ArrayList<Integer>> posiblaity = getSubsets(intArray);
		Dominant(posiblaity, n);
	} 
}
