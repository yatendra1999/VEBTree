/*
Yatendra Singh- 11741190
Ruskin Raj Manku - 11740790
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;


/*
* This is the implementation for a Van Embde Boas Tree.
* This tree reduces the time of operations to O(loglogU) from O(logN) reducing the total time taken for the code to run.
* */


public class VEBTreeTest2
{
	public static void main(String[] args) throws IOException {
	    //take the start value of time as current time
        long t_start = System.currentTimeMillis();
	    try
        {
            // open the file input.txt as the input for the tree.
            FileReader bufferread = new FileReader("input.txt");
            StreamTokenizer token = new StreamTokenizer(bufferread);
            token.resetSyntax();
            token.whitespaceChars(0, ' ');
            token.wordChars(48, 122);
            //the first line contains the universe size(U) of the tree.
            int n;
            token.nextToken();
            n = Integer.parseInt(token.sval);
            VEBTree postman = VEBTree.createVEBTree(n);
            token.nextToken();
            n = Integer.parseInt(token.sval);
            //create a hashmap for the info of where to send the packet.
            HashMap<Integer, String> hmap = new HashMap<>();
            //the next n lines contain the addresses of the respective routing boxes.
            for (int i = 0; i < n; i++) {
                token.nextToken();
                int z = Integer.parseInt(token.sval);
                token.nextToken();
                String s = token.sval;
                hmap.put(z, s);
                postman.insert(z);
            }
            //this prints the value of all the routing boxes
            print(hmap);
            token.nextToken();
            int val;
            //the next n lines represent the queries given by the user as 1:predecessor 2:insert 3:delete
            n = Integer.parseInt(token.sval);
            System.out.println(n);
            for (int i = 0; i < n; i++)
            {
                token.nextToken();
                int z = Integer.parseInt(token.sval);
                switch (z) {
                    case 1:
                        token.nextToken();
                        val = Integer.parseInt(token.sval);
                        if (postman.search(val))
                            System.out.println("The packet " + val + " will be routed to " + hmap.get(val));
                        else
                            System.out.println("The packet " + val + " will be routed to " + hmap.get(postman.predecessor(val)));
                        break;
                    case 2:
                        token.nextToken();
                        val = Integer.parseInt(token.sval);
                        if (hmap.containsKey(val))
                            hmap.remove(val);
                        postman.delete(val);
                        break;
                    case 3:
                        token.nextToken();
                        val = Integer.parseInt(token.sval);
                        System.out.println("Inserting "+val+" in the tree.");
                        if (hmap.containsKey(val))
                            System.out.println("Already exists.");
                        else {
                            token.nextToken();
                            String s = token.sval;
                            hmap.put(val, s);
                        }
                        postman.insert(val);
                        break;
                }
            }
            bufferread.close();
        } catch(Exception e){ System.out.println("Error "+e+" please try again"); }
        long t_end = System.currentTimeMillis();
	    // outputs the total time taken for the code to run
	    System.out.println("\n\nThe time taken for the tree is " + (t_end-t_start)+" milliseconds.");
    }
    public static void print(Map<Integer, String> map)
    {
        if (map.isEmpty())
        {
            System.out.println("map is empty");
        }

        else
        {
            System.out.println(map);
        }
    }
}
