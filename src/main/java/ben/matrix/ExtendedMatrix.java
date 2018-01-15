package ben.matrix;

import org.ejml.data.*;
import org.ejml.ops.ConvertDMatrixStruct;
import org.ejml.ops.ConvertFMatrixStruct;
import org.ejml.simple.SimpleBase;

import java.util.function.Function;

public class ExtendedMatrix extends SimpleBase<ExtendedMatrix> {

    public ExtendedMatrix() {

    }

    public ExtendedMatrix(int numRows, int numCols, MatrixType type) {
        switch(type) {
            case DDRM:
                this.setMatrix(new DMatrixRMaj(numRows, numCols));
                break;
            case FDRM:
                this.setMatrix(new FMatrixRMaj(numRows, numCols));
                break;
            case ZDRM:
                this.setMatrix(new ZMatrixRMaj(numRows, numCols));
                break;
            case CDRM:
                this.setMatrix(new CMatrixRMaj(numRows, numCols));
                break;
            case DSCC:
                this.setMatrix(new DMatrixSparseCSC(numRows, numCols));
                break;
            case FSCC:
                this.setMatrix(new FMatrixSparseCSC(numRows, numCols));
                break;
            default:
                throw new RuntimeException("Unknown matrix type");
        }

    }

    public ExtendedMatrix(Matrix orig) {
        Object mat;
        if (orig instanceof DMatrixRBlock) {
            DMatrixRMaj a = new DMatrixRMaj(orig.getNumRows(), orig.getNumCols());
            ConvertDMatrixStruct.convert((DMatrixRBlock)orig, a);
            mat = a;
        } else if (orig instanceof FMatrixRBlock) {
            FMatrixRMaj a = new FMatrixRMaj(orig.getNumRows(), orig.getNumCols());
            ConvertFMatrixStruct.convert((FMatrixRBlock)orig, a);
            mat = a;
        } else {
            mat = orig.copy();
        }

        this.setMatrix((Matrix)mat);
    }

    protected ExtendedMatrix createMatrix(int numRows, int numCols, MatrixType matrixType) {
        return new ExtendedMatrix(numRows, numCols, matrixType);
    }

    public static ExtendedMatrix wrap(Matrix matrix) {
        ExtendedMatrix e = new ExtendedMatrix();
        e.mat = matrix;
        return e;
    }

    protected ExtendedMatrix wrapMatrix(Matrix matrix) {
        ExtendedMatrix e = new ExtendedMatrix();
        e.mat = matrix;
        return e;
    }

    public ExtendedMatrix perform(Function<Double, Double> f) {
        ExtendedMatrix m = createMatrix(this.numRows(), this.numCols(), this.mat.getType());

        for(int i = 0; i < this.getNumElements(); ++i) {
            m.set(i, f.apply(this.get(i)));
        }

        return m;
    }
}
