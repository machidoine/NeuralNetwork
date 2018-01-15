package ben;

import ben.neuronal.NeuralNetwork;
import org.ejml.data.DMatrixRMaj;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        NeuralNetwork net = new NeuralNetwork(List.of(4, 5, 6, 7));

        DMatrixRMaj result = net.feedForward(new DMatrixRMaj(new double[][]{{1.0}, {2.0}, {3.0}, {4.0}}));

        System.out.println(result);

//        NeuralNetwork neuralNetwork = new NeuralNetwork(4, 1);
//        neuralNetwork.addLayer(3);
//
//        LearningData learningData = null;
//        neuralNetwork.learn(learningData);
//
//
//        neuralNetwork.run();
//
//        Graph<Neural, NeuralLink> neuralNetwork = new SimpleGraph<>(NeuralLink.class);
//        System.out.println( "Hello World!" );
    }
}
