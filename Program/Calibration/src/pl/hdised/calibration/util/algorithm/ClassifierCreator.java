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
import weka.classifiers.rules.*;
import weka.classifiers.trees.*;
import weka.classifiers.trees.lmt.LogisticBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sebastian Oprzędek on 04.09.2017.
 */
public class ClassifierCreator {

    private static final Map<Integer, String> names;

    static{
        // comented out algorithms throw Exceptions or need research to use
        names = new HashMap<Integer, String>();
//        names.put(0, "AdaBoostM1");
        names.put(1, "AdditiveRegression");
//        names.put(2, "AttributeSelectedClassifier");
        names.put(3, "Bagging");
//        names.put(4, "BayesNet");
//        names.put(5, "BayesNetGenerator");
//        names.put(6, "BIFReader");
//        names.put(7, "ClassificationViaRegression");
//        names.put(8, "CostSensitiveClassifier");
//        names.put(9, "CVParameterSelection"); // returns always 14439,350948
//        names.put(10, "DecisionStump"); // classify to 9430,522813 or 26824,385271
        names.put(11, "DecisionTable");
//        names.put(12, "EditableBayesNet");
//        names.put(13, "FilteredClassifier");
//        names.put(14, "GaussianProcesses");
//        names.put(16, "HoeffdingTree");
        names.put(17, "IBk");
//        names.put(18, "InputMappedClassifier"); // returns always 14497,192764
//        names.put(20, "IterativeClassifierOptimizer");
//        names/.put(21, "J48");
//        names.put(22, "JRip");
//        names.put(23, "KStar");// too long execution
//        names.put(24, "LinearRegression");
//        names.put(25, "LMT");
//        names.put(27, "Logistic");
//        names.put(28, "LogisticBase"); // returns always 1.0
//        names.put(29, "LogitBoost");
//        names.put(30, "LWL");// too long execution
//        names.put(32, "M5P");//NoClassDefFoundError: no/uib/cipr/matrix/Matrix
//        names.put(33, "M5Rules"); //NoClassDefFoundError: no/uib/cipr/matrix/Matrix
//        names.put(34, "MultiClassClassifier");
//        names.put(35, "MultiClassClassifierUpdateable");
        names.put(36, "MultilayerPerceptron");
//        names.put(38, "MultiScheme"); // returns always 14497,192764
//        names.put(39, "NaiveBayes");
//        names.put(40, "NaiveBayesMultinomial");
//        names.put(41, "NaiveBayesMultinomialText");
//        names.put(42, "NaiveBayesMultinomialUpdateable");
//        names.put(43, "NaiveBayesUpdateable");
//        names.put(45, "OneR");
//        names.put(48, "PART");
        names.put(51, "RandomCommittee");
        names.put(52, "RandomForest");
        names.put(54, "RandomizableFilteredClassifier");
//        names.put(60, "RandomSubSpace"); // classify into groups
        names.put(61, "RandomTree");
//        names.put(63, "RegressionByDiscretization"); // too long execution
        names.put(64, "REPTree");
//        names.put(67, "SerializedClassifier"); // FileNotFoundException ?
//        names.put(68, "SGD");
//        names.put(69, "SGDText");
//        names.put(70, "SimpleLinearRegression"); // classify into groups
//        names.put(71, "SimpleLogistic");
//        names.put(73, "SMO");
//        names.put(74, "SMOreg");
//        names.put(75, "Stacking"); // always 144497,l192764
//        names.put(78, "Vote"); // always 144497,l192764
//        names.put(79, "VotedPerceptron");
//        names.put(80, "WeightedInstancesHandlerWrapper"); // always 144497,l192764
//        names.put(81, "ZeroR"); // always 144497,l192764
    }

    public Classifier createClassifier(String className) throws Exception {
        for(Integer key : names.keySet()) {
            if(names.get(key)==className)
                return createClassifier(key);
        }
        return null;
    }

    public Classifier createClassifier(int index) throws Exception {
        switch (index) {
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
            case 81: return new ZeroR();
            default: return null;
        }
    }

    public List<String> getNames(){
        List<String> strings = new ArrayList<>();
        for(int i=0; i<100; i++){
            if(names.get(i)!=null)
            strings.add(names.get(i));
        }
        return strings;
    }
}
