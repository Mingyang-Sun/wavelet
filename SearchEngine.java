import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler
{
    ArrayList<String> list = new ArrayList<>();

    public String handleRequest(URI url)
    {
        if (url.getPath().equals("/"))
        {
            return "No action";
        } 
        else
        {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) 
            {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s"))
                {
                    list.add(parameters[1]);
                    return String.format("%s has been added", parameters[1]);
                }
            }
            else if (url.getPath().contains("/search"))
            {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s"))
                {
                  String empty = "";  
                  for (String e : list)
                  {
                    if (e.contains(parameters[1]))
                    {
                      empty += e;
                      empty += " ";
                    }
                  }
                  return empty;
                }
            }
            return "404 Not Found!";
        }
    }
}

class SearchEngine
{
    public static void main(String[] args) throws IOException
    {
        if(args.length == 0)
        {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        int port = Integer.parseInt(args[0]);
        Server.start(port, new Handler());
    }
}