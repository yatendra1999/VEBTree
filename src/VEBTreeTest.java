import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class VEBTreeTest
{
	public static void main(String[] args) throws IOException
	{
        FileReader bufferread = new FileReader("input.txt");
        StreamTokenizer token = new StreamTokenizer(bufferread);
        token.resetSyntax();
        token.whitespaceChars(0,' ');
        token.wordChars(48,122);
        int n;
        token.nextToken();
        n= Integer.parseInt(token.sval);
        VEBTree postman = VEBTree.createVEBTree(n);
        postman.insert(3);
        postman.insert(1);
        System.out.println(postman.predecessor(4));
        postman.delete(3);
        System.out.println(postman.predecessor(4));
        token.nextToken();
        n = Integer.parseInt(token.sval);
        HashMap<Integer, String> hmap = new HashMap<>();
        for(int i=0;i<=n;i++)
        {
            token.nextToken();
            int z = Integer.parseInt(token.sval);
            token.nextToken();
            String s = token.sval;
            hmap.put(z,s);
            postman.insert(z);
        }
        print(hmap);
        token.nextToken();
        int val;
        n = Integer.parseInt(token.sval);
        for(int i=0;i<=n;i++)
        {
            token.nextToken();
            int z= Integer.parseInt(token.sval);
            switch(z)
            {
                case 1 :
                    token.nextToken();
                    val = Integer.parseInt(token.sval);
                    if(hmap.containsKey(val))
                        System.out.println("The packet "+val+" will be routed too"+hmap.get(val));
                    else
                        System.out.println("The packet "+val+" will be routed to "+hmap.get(postman.predecessor(val)));
                    break;
                case 2:
                    token.nextToken();
                    val = Integer.parseInt(token.sval);
                    if(hmap.containsKey(val))
                        hmap.remove(val);
                    postman.delete(val);
                    break ;
                case 3:
                    token.nextToken();
                    val = Integer.parseInt(token.sval);
                    if(hmap.containsKey(val))
                        System.out.println("Already exists.");
                    else
                    {
                        token.nextToken();
                        String s = token.sval;
                        hmap.put(val,s);
                    }
                    postman.insert(val);
                    break;
            }
        }
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
