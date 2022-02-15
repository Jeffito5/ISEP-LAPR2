package app.domain.model.algorithms.BruteForceAlgorithm;

import com.isep.mdis.Sum;

import java.util.Arrays;

public class BruteForceAlgorithm{
    public BruteForceAlgorithm(){};

    public static int[] Max(int[] sequence){
        int lowerIndex=0;
        int upperIndex=0;
        int maxSum=0;
        for(int i=0; i<sequence.length;i++){
            int sumSoFar=0;
            for(int e=i; e<sequence.length;e++){
                sumSoFar+=sequence[e];
                if(sumSoFar>maxSum){
                    maxSum=sumSoFar;
                    lowerIndex=i;
                    upperIndex=e+1;
                }
            }
        }

        return Arrays.copyOfRange(sequence, lowerIndex, upperIndex);
    }



}
