package ben.neuronal;

import ben.matrix.ExtendedMatrix;
import ben.stream.StreamUtils;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.RandomMatrices_DDRM;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NeuralNetwork<I, O> {

    private final List<DMatrixRMaj> weights;
    private final List<DMatrixRMaj> biais;

    public NeuralNetwork(List<Integer> layerSizes) {
        assert layerSizes.size() > 1;

        biais = IntStream.range(1, layerSizes.size())
                .mapToObj(i -> RandomMatrices_DDRM.rectangle(layerSizes.get(i), 1, 0, 10, new Random(new Date().getTime())))
                .collect(Collectors.toList());

        weights = IntStream.range(1, layerSizes.size())
                .mapToObj(i -> RandomMatrices_DDRM.rectangle(layerSizes.get(i), layerSizes.get(i - 1), -1, 1, new Random(new Date().getTime())))
                .collect(Collectors.toList());
    }

    public DMatrixRMaj feedForward(DMatrixRMaj inputM) {
        assert inputM.numCols == 1 && inputM.numRows == weights.get(0).numCols : "the input must have the same size as the first weight layer columns";

        ExtendedMatrix input = ExtendedMatrix.wrap(inputM);
        Stream<ExtendedMatrix> Ws = weights.stream().map(ExtendedMatrix::new);
        Stream<ExtendedMatrix> Bs = biais.stream().map(ExtendedMatrix::new);

        return StreamUtils.zipReduce(Ws, Bs, input, (r, w, b) -> w.mult(r).plus(b).perform(ActivationFunction::sigmoid)).getMatrix();
    }

}
