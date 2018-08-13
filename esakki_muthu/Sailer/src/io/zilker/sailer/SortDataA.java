package io.zilker.sailer;

import java.util.Comparator;

class SortDataA implements Comparator<GroupA>{
    

    public int compare(GroupA o1, GroupA o2) {
        
        
        int o1Total=o1.apple+o1.banana+o1.mango;
        
        int o2Total=o2.apple+o2.banana+o2.mango;
        
        return o2Total - o1Total;
        
    }
    
    
}
