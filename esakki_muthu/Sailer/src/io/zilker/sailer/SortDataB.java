package io.zilker.sailer;

import java.util.Comparator;

class SortDataB implements Comparator<GroupB>{
    

    public int compare(GroupB o1, GroupB o2) {
        
        
        int o1Total=o1.bigFish+o1.smalFish;
        
        int o2Total=o2.bigFish+o2.smalFish;
        
        return o2Total - o1Total;
        
    }
    
    
}

