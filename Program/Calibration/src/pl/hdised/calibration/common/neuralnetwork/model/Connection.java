package pl.hdised.calibration.common.neuralnetwork.model;

/**
 * Represents connection between neurons
 * Created by Sebastian Oprzędek on 18.07.2017.
 */
class Connection {
    double weight;
    double deltaWeight;

    /**
     * Creates connection with forwarded weight
     *
     * @param weight connection weight
     */
    Connection(double weight) {
        this.weight = weight;
    }
}
