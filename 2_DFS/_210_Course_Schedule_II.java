class Solution {
	
    private int N = 0;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
		
        int[] result = new int[numCourses];
        Course[] courses = new Course[numCourses];
		
        for (int i = 0; i < numCourses; i++) { 
            courses[i] = new Course(i);
        }
		
        for (int i = 0; i < prerequisites.length; i++) {
            courses[prerequisites[i][0]].add(courses[prerequisites[i][1]]);
        }
		
        for (int i = 0; i < numCourses; i++) {
			
            if (isCyclic(courses[i], result))  return new int[0];  // return emtpy array if cyclic
             
        }
		
        return result;
    }

    private boolean isCyclic(Course cur, int[] result) {  // if cyclic, then no solution
		
        if (cur.tested == true) return false;  // Check if cyclic 
		
        if (cur.visited == true) return true;  // visited before --> cyclic!!!
		
        cur.visited = true;          // confirmed visited 
			
        for (Course c: cur.pre) {
            if (isCyclic(c, result)) {
                return true;
            }
        }
		
        cur.tested = true;   	    //  confirmed already tested OK  
		
        result[N++] = cur.number;   // build such result 
		
        return false;
    }

    class Course { 
		
        boolean visited = false;
		
        boolean tested = false;
		
        int number;
		
        List < Course > pre = new ArrayList < Course > ();
		
        public Course(int i) {
            number = i;
        }
		
        public void add(Course c) {
            pre.add(c);
        }
    }
}