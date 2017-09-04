package pl.hdised.calibration.util.algorithm;

import weka.classifiers.*;
import weka.classifiers.bayes.*;
import weka.classifiers.bayes.net.*;
import weka.classifiers.functions.*;
import weka.classifiers.lazy.IBk;
import weka.classifiers.lazy.KStar;
import weka.classifiers.lazy.LWL;
import weka.classifiers.meta.*;
import weka.classifiers.misc.*;
import weka.classifiers.pmml.consumer.*;
import weka.classifiers.rules.*;
import weka.classifiers.trees.*;
import weka.classifiers.trees.lmt.LogisticBase;

/**
 * Created by Sebastian Oprzędek on 04.09.2017.
 */
public class ClassifierCreator {

    public Classifier createClassifier(int index) throws Exception{
        switch (index){
            case 0: return new AdaBoostM1();
            case 1: return new AdditiveRegression();
            case 2: return new AttributeSelectedClassifier();
            case 3: return new Bagging();
            case 4: return new BayesNet();
            case 5: return new BayesNetGenerator();
            case 6: return new BIFReader();
            case 7: return new ClassificationViaRegression();
            case 8: return new CostSensitiveClassifier();
            case 9: return new CVParameterSelection();
            case 10: return new DecisionStump();
            case 11: return new DecisionTable();
            case 12: return new EditableBayesNet();
            case 13: return new FilteredClassifier();
            case 14: return new GaussianProcesses();
            case 16: return new HoeffdingTree();
            case 17: return new IBk();
            case 18: return new InputMappedClassifier();
            case 20: return new IterativeClassifierOptimizer();
            case 21: return new J48();
            case 22: return new JRip();
            case 23: return new KStar();
            case 24: return new LinearRegression();
            case 25: return new LMT();
            case 27: return new Logistic();
            case 28: return new LogisticBase();
            case 29: return new LogitBoost();
            case 30: return new LWL();
            case 32: return new M5P();
            case 33: return new M5Rules();
            case 34: return new MultiClassClassifier();
            case 35: return new MultiClassClassifierUpdateable();
            case 36: return new MultilayerPerceptron();
            case 38: return new MultiScheme();
            case 39: return new NaiveBayes();
            case 40: return new NaiveBayesMultinomial();
            case 41: return new NaiveBayesMultinomialText();
            case 42: return new NaiveBayesMultinomialUpdateable();
            case 43: return new NaiveBayesUpdateable();
            case 45: return new OneR();
            case 48: return new PART();
            case 51: return new RandomCommittee();
            case 52: return new RandomForest();
            case 54: return new RandomizableFilteredClassifier();
            case 60: return new RandomSubSpace();
            case 61: return new RandomTree();
            case 63: return new RegressionByDiscretization();
            case 64: return new REPTree();
            case 67: return new SerializedClassifier();
            case 68: return new SGD();
            case 69: return new SGDText();
            case 70: return new SimpleLinearRegression();
            case 71: return new SimpleLogistic();
            case 73: return new SMO();
            case 74: return new SMOreg();
            case 75: return new Stacking();
            case 78: return new Vote();
            case 79: return new VotedPerceptron();
            case 80: return new WeightedInstancesHandlerWrapper();
            default: return new ZeroR();
        }
    }

    public String getClassifierName(int index){
        switch (index){
            case 0: return "AdaBoostM1";
            case 1: return "AdditiveRegression";
            case 2: return "AttributeSelectedClassifier";
            case 3: return "Bagging";
            case 4: return "BayesNet";
            case 5: return "BayesNetGenerator";
            case 6: return "BIFReader";
            case 7: return "ClassificationViaRegression";
            case 8: return "CostSensitiveClassifier";
            case 9: return "CVParameterSelection";
            case 10: return "DecisionStump";
            case 11: return "DecisionTable";
            case 12: return "EditableBayesNet";
            case 13: return "FilteredClassifier";
            case 14: return "GaussianProcesses";
            case 16: return "HoeffdingTree";
            case 17: return "IBk";
            case 18: return "InputMappedClassifier";
            case 20: return "IterativeClassifierOptimizer";
            case 21: return "J48";
            case 22: return "JRip";
            case 23: return "KStar";
            case 24: return "LinearRegression";
            case 25: return "LMT";
            case 27: return "Logistic";
            case 28: return "LogisticBase";
            case 29: return "LogitBoost";
            case 30: return "LWL";
            case 32: return "M5P";
            case 33: return "M5Rules";
            case 34: return "MultiClassClassifier";
            case 35: return "MultiClassClassifierUpdateable";
            case 36: return "MultilayerPerceptron";
            case 38: return "MultiScheme";
            case 39: return "NaiveBayes";
            case 40: return "NaiveBayesMultinomial";
            case 41: return "NaiveBayesMultinomialText";
            case 42: return "NaiveBayesMultinomialUpdateable";
            case 43: return "NaiveBayesUpdateable";
            case 45: return "OneR";
            case 48: return "PART";
            case 51: return "RandomCommittee";
            case 52: return "RandomForest";
            case 54: return "RandomizableFilteredClassifier";
            case 60: return "RandomSubSpace";
            case 61: return "RandomTree";
            case 63: return "RegressionByDiscretization";
            case 64: return "REPTree";
            case 67: return "SerializedClassifier";
            case 68: return "SGD";
            case 69: return "SGDText";
            case 70: return "SimpleLinearRegression";
            case 71: return "SimpleLogistic";
            case 73: return "SMO";
            case 74: return "SMOreg";
            case 75: return "Stacking";
            case 78: return "Vote";
            case 79: return "VotedPerceptron";
            case 80: return "WeightedInstancesHandlerWrapper";
            case 81: return "ZeroR";
            default: return null;
        }
    }

}
