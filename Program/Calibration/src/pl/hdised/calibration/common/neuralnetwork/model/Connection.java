package pl.hdised.calibration.common.neuralnetwork.model;

/**
 * Created by Sebastian Oprzędek on 18.07.2017.
 */
class Connection {
    double weight;
    double deltaWeight;

    Connection(double weight){
        this.weight = weight;
    }
}
