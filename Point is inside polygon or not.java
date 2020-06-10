

public class Polygon 
{ 
 
    static class Point  
    { 
        double x; 
        double y; 
  
        public Point(double x, double y) 
        { 
            this.x = x; 
            this.y = y; 
        } 
    }; 
  
    static boolean onSide(Point p, Point q, Point r)  
    { 
        if (q.x <= Math.max(p.x, r.x) && 
            q.x >= Math.min(p.x, r.x) && 
            q.y <= Math.max(p.y, r.y) && 
            q.y >= Math.min(p.y, r.y)) 
        { 
            return true; 
        } 
        return false; 
    } 
  
    
    static int direction(Point p, Point q, Point r)  
    { 
        double val = (q.y - p.y) * (r.x - q.x) 
                - (q.x - p.x) * (r.y - q.y); 
  
        if (val == 0)  
        { 
            return 0;  
        } 
        else if(val > 0) 
            return 1;
        else 
            return 2;  
    } 
  
    static boolean doIntersect(Point p1, Point q1,  
                               Point p2, Point q2)  
    { 
        
        int d1 = direction(p1, q1, p2); 
        int d2 = direction(p1, q1, q2); 
        int d3 = direction(p2, q2, p1); 
        int d4 = direction(p2, q2, q1); 
  
        
        if (d1 != d2 && d3 != d4) 
        { 
            return true; 
        } 
  
        
        if (d1 == 0 && onSide(p1, p2, q1))  
        { 
            return true; 
        } 
  
       
        if (d2 == 0 && onSide(p1, q2, q1))  
        { 
            return true; 
        } 
  
        
        if (d3 == 0 && onSide(p2, p1, q2)) 
        { 
            return true; 
        } 
 
 
        if (d4 == 0 && onSide(p2, q1, q2)) 
        { 
            return true; 
        } 
  
        return false;  
    } 
  
     
    static boolean isInside(Point polygon[], Point p) 
    { 
         int n = polygon.length;
        
        if (n < 3)  
        { 
            return false; 
        } 
            int Infinite_Point=500;
        
        Point extreme = new Point(Infinite_Point, p.y); 
  
       
        int count = 0, i = 0; 
        do 
        { 
            int next = (i + 1) % n; 
  
            if (doIntersect(polygon[i], polygon[next], p, extreme))  
            {  
                if (direction(polygon[i], p, polygon[next]) == 0) 
                { 
                    return onSide(polygon[i], p, 
                                     polygon[next]); 
                } 
  
                count++; 
            } 
            i = next; 
        } while (i != 0); 
  
        
        return (count%2==1); 
    } 
  
    
    public static void main(String[] args)  
    { 
        Point polygon1[] = {new Point(1, 0), 
                            new Point(8, 3),  
                            new Point(8, 8),  
                            new Point(1, 5)}; 
        
        Point p = new Point(3,5); 
        if (isInside(polygon1, p)) 
        { 
            System.out.println("True"); 
        }  
        else 
        { 
            System.out.println("False"); 
        } 
        
        Point polygon2[] = {new Point(-3, 2),  
                            new Point(-2, -0.8),
                            new Point(0, 1.2),
                            new Point(2.2, 0),
                            new Point(2, 4.5)}; 
                            
        p = new Point(0, 0); 
      
        if (isInside(polygon2, p))  
        { 
            System.out.println("True"); 
        }  
        else 
        { 
            System.out.println("False"); 
        } 
       
    } 
} 

