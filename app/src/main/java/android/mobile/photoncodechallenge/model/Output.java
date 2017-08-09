package android.mobile.photoncodechallenge.model;

/**
 * Created by Raj
 * on 8/8/2017.
 */

public class Output {
    private boolean isCompletePath;
    private int lowestPathCost;
    private int [] path;

   public Output(boolean isCompletePath,int lowestPathCost,int [] path){
       this.isCompletePath=isCompletePath;
       this.lowestPathCost=lowestPathCost;
       this.path=path;
   }

    public boolean isCompletePath() {
        return isCompletePath;
    }

    public void setCompletePath(boolean completePath) {
        isCompletePath = completePath;
    }

    public int getLowestPathCost() {
        return lowestPathCost;
    }

    public void setLowestPathCost(int lowestPathCost) {
        this.lowestPathCost = lowestPathCost;
    }

    public int[] getPath() {
        return path;
    }

    public void setPath(int[] path) {
        this.path = path;
    }
}
