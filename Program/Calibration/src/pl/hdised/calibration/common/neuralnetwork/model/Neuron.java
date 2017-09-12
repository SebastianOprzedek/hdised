package pl.hdised.calibration.common.neuralnetwork.model;

/**
 * Represents model of neuron
 * Created by Sebastian Oprzędek on 18.07.2017.
 */
public class Neuron {

    private int index;
    double outputValue;
    Connection[] outputWeights;
    double gradient;
    double alpha;    //0.0 - n multiplier of the last weight change (momentum)
    double eta; //0.0 - 1.0 training rate

    public double getOutputValue() {
        return outputValue;
    }

    public void setOutputValue(double value) {
        outputValue = value;
    }

    /**
     * Creates neuron model
     *
     * @param howManyOutputs number of connections to next layer neurons to create
     * @param _myIndex       index of neuron on layer
     * @param _alpha         net momentum
     * @param _eta           learning grade
     */
    public Neuron(int howManyOutputs, int _myIndex, double _alpha, double _eta) {
        index = _myIndex;
        alpha = _alpha;
        eta = _eta;
        outputWeights = new Connection[howManyOutputs];
        for (int i = 0; i < howManyOutputs; ++i)
            outputWeights[i] = new Connection(Math.random());
    }

    /**
     * standard feed forward algorithm using neurons from previous layer
     *
     * @param previousLayer previous layer
     */
    void feedForward(Neuron[] previousLayer) {
        double sum = 0.0;
        for (int whichNeuron = 0; whichNeuron < previousLayer.length; ++whichNeuron)
            sum += previousLayer[whichNeuron].getOutputValue() * previousLayer[whichNeuron].outputWeights[index].weight;
        outputValue = transferFunction(sum);
    }

    /**
     * transfer function used in feed forward.
     * Uses standard tanh(x)
     * Other transfer functions are to check
     *
     * @param x value to transfer
     * @return transfered value
     */
    double transferFunction(double x) {
        //TODO: Check other transfer functions
        return Math.tanh(x);
    }

    /**
     * transfer function derivative for standard tanh(x)
     *
     * @param x transfered value
     * @return normal value
     */
    double transferFunctionDerivative(double x) {
        return 1.0 - x * x;
    }

    /**
     * calculates gradients for back propagation
     *
     * @param targetValue target value
     */
    void calcOutputGradients(double targetValue) {
        double delta = targetValue - outputValue;
        gradient = delta * transferFunctionDerivative(outputValue);
    }

    /**
     * calculates hidden gradients for back propagation
     *
     * @param nextLayer next layer
     */
    void calcHiddenGradients(Neuron[] nextLayer) {
        double dow = sumDOW(nextLayer);
        gradient = dow * transferFunctionDerivative(outputValue);
    }

    /**
     * sum our contributions of the errors at the nodes we feed
     *
     * @param nextLayer next layer
     * @return sum our contributions of the errors at the nodes we feed
     */
    double sumDOW(Neuron[] nextLayer) {
        double sum = 0.0;    //
        for (int whichNeuron = 0; whichNeuron < nextLayer.length - 1; ++whichNeuron)
            sum += outputWeights[whichNeuron].weight * nextLayer[whichNeuron].gradient;
        return sum;
    }

    /**
     * updates input weights of neuron connections during back propagation
     *
     * @param previousLayer previous layer
     */
    void updateInputWeights(Neuron[] previousLayer) {
        for (Neuron neuron : previousLayer) {
            double oldDeltaWeight = neuron.outputWeights[index].deltaWeight;
            double newDeltaWeight =
                    eta //train rate
                            * neuron.getOutputValue() //individual input
                            * gradient
                            //a fraction of the previous delta weight
                            + alpha //momentum
                            * oldDeltaWeight;
            neuron.outputWeights[index].deltaWeight = newDeltaWeight;
            neuron.outputWeights[index].weight += newDeltaWeight;
        }
    }

    /**
     * prints neuron to string
     * it allows to save the net
     *
     * @return string containing all neuron data
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(index).append(" ").append(outputValue).append(" ").append(gradient);
        for (int i = 0; i < outputWeights.length; i++)
            stringBuilder.append(" ").append(outputWeights[i].weight).append(" ").append(outputWeights[i].deltaWeight);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    /**
     * updates neuron values by forwarded values
     * it use th same schema which is used in toString method
     *
     * @param values values containing all neuron data
     */
    void update(double[] values) {
        outputValue = values[1];
        gradient = values[2];
        for (int i = 0; i < outputWeights.length; ++i) {
            outputWeights[i].weight = values[2 * i + 3];
            outputWeights[i].deltaWeight = values[2 * i + 4];
        }
    }

}
