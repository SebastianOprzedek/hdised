package pl.hdised.calibration.common.neutralnetwork.model;

/**
 * Created by Sebastian Oprzędek on 18.07.2017.
 */
public class Neuron {

	private int index;
    double outputValue;
    Connection[] outputWeights;
    double gradient;
    double alpha;	//0.0 - n multiplier of the last weight change (momentum)
    double eta; //0.0 - 1.0 training rate

    public double getOutputValue() { return outputValue; }
    public void setOutputValue(double value) { outputValue = value; }

    public Neuron(int howManyOutputs, int _myIndex, double _alpha, double _eta)
    {
        index = _myIndex;
        alpha = _alpha;
        eta = _eta;
        outputWeights = new Connection[howManyOutputs];
        for (int i = 0; i < howManyOutputs; ++i)	{
            outputWeights[i].weight = Math.random();
        }
    }

    void feedForward(Neuron[] previousLayer)
    {
        double sum = 0.0;
        for (int whichNeuron = 0; whichNeuron < previousLayer.length; ++whichNeuron)
            sum += previousLayer[whichNeuron].getOutputValue() * previousLayer[whichNeuron].outputWeights[index].weight;
        outputValue = transferFunction(sum);
    }

    double transferFunction(double x)
    {
        //TODO: Check other transfer functions
        return Math.tanh(x);
    }

    double transferFunctionDerivative(double x)
    {
        return 1.0 - x*x;
    }

    void calcOutputGradients(double targetValue)
    {
        double delta = targetValue - outputValue;
        gradient = delta * transferFunctionDerivative(outputValue);
    }

    void calcHiddenGradients(Neuron[] nextLayer)
    {
        double dow = sumDOW(nextLayer);
        gradient = dow * transferFunctionDerivative(outputValue);
    }

    double sumDOW(Neuron[] nextLayer)
    {
        double sum = 0.0;	//sum our contributions of the errors at the nodes we feed
        for (int whichNeuron = 0; whichNeuron < nextLayer.length - 1; ++whichNeuron)
            sum += outputWeights[whichNeuron].weight * nextLayer[whichNeuron].gradient;
        return sum;
    }

    void updateInputWeights(Neuron[] previousLayer)
    {
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

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(index).append(" ").append(outputValue).append(" ").append(gradient);
        for (int i = 0; i < outputWeights.length; i++)
            stringBuilder.append(" ").append(outputWeights[i].weight).append(" ").append(outputWeights[i].deltaWeight);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    void update(double[] values) {
        outputValue = values[1];
        gradient = values[2];
        for (int i = 0; i < outputWeights.length; ++i) {
            int a = values.length;
            int b = 2 * i + 4;
            int c = outputWeights.length;
            outputWeights[i].weight = values[2*i+3];
            outputWeights[i].deltaWeight = values[2*i+4];
        }
    }
    
}
