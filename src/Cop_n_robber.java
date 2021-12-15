import java.util.ArrayList;

public class Cop_n_robber{

    public boolean end = false,rob = false;
    public int length = 0;
    public void checkCycle(int v,ArrayList<ArrayList<Integer>>adj) {
        boolean visited[] = new boolean[v];
        for(int i = 0;i<v;i++)
        visited[i] = false;    
        this.length = 0;

        for(int i = 0;i<v;i++)
        {
            if(visited[i] == false)
            {
                if(this.dfs(i,visited,-1,adj) == true)
                {
                    if(this.length >= 4)
                    {
                        this.rob = true;
                    }
                    this.length = 0;
                }
            }
        }
    }
    public boolean dfs(int i,boolean visited[],int parent,ArrayList<ArrayList<Integer>>adj) {
        visited[i] = true;
        for(int j = 0;j<adj.get(i).size();j++)
        {
            if(this.end)
            {
                this.length = 0;
            }
            if(visited[j] == false)
            {
                this.length+=1;
                if(this.dfs(j,visited,i,adj));
                return true;
            }
            else if(parent != j)
            return true;
        }
        this.end = true;
        return false;
    }
}