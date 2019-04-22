#include <iostream>
#include <string>
#include <random>
#include <math.h>
#include <unordered_map>
#include <iomanip>

using namespace std;

int numRepeats = 1;
int seed = 1;

void inputArgs(int argc, char* argv[]) {
  if (argc == 1)
    return;
  else if (argc == 3) {
    if (strcmp(argv[1], "-n") == 0 ) {
      try {
        numRepeats = stoi(argv[2]);
      }
      catch (exception e) {
        cout << "Arguments must either be -n [int] , -s [int] , or both" << endl;
        return;
      }
    }
    else if (strcmp(argv[1], "-s") == 0) {
      try {
        seed = stoi(argv[2]);
      }
      catch (exception e) {
        cout << "Arguments must either be -n [int] , -s [int] , or both" << endl;
        return;
      }
    }
    else {
      cout << "Arguments must either be -n [int] , -s [int] , or both" << endl;
      return;
    }
  }
  else if (argc == 5) {
    if (strcmp(argv[1], "-n") == 0 ) {
      try {
        numRepeats = stoi(argv[2]);
      }
      catch (exception e) {
        cout << "Arguments must either be -n [int] , -s [int] , or both" << endl;
        return;
      }
    }
    else if (strcmp(argv[1], "-s") == 0) {
      try {
        seed = stoi(argv[2]);
      }
      catch (exception e) {
        cout << "Arguments must either be -n [int] , -s [int] , or both" << endl;
        return;
      }
    }
    else {
      cout << "Arguments must either be -n [int] , -s [int] , or both" << endl;
      return;
    }
    if (strcmp(argv[3], "-n") == 0 ) {
      try {
        numRepeats = stoi(argv[4]);
      }
      catch (exception e) {
        cout << "Arguments must either be -n [int] , -s [int] , or both" << endl;
        return;
      }
    }
    else if (strcmp(argv[3], "-s") == 0) {
      try {
        seed = stoi(argv[4]);
      }
      catch (exception e) {
        cout << "Arguments must either be -n [int] , -s [int] , or both" << endl;
        return;
      }
    }
    else {
      cout << "Arguments must either be -n [int] , -s [int] , or both" << endl;
      return;
    }
  }
  else 
    cout << "Arguments must either be -n [int] , -s [int] , or both" << endl;
}

double createRandomDouble(double min, double max) {
  double zeroToOne = ((double) rand() / RAND_MAX);
  return (((max - min) * zeroToOne) + min);
}  

unordered_map<string, double> createMap() {

  unordered_map<string, double> outputMap;

  outputMap["pomposity"] = createRandomDouble(0.0, 1.0);
  outputMap["learning_curve"] = createRandomDouble(1.0, 100.0);
  outputMap["optimism"] = createRandomDouble(0.1, 10.0);
  outputMap["atleast"] =  createRandomDouble(0.0, 100.0);
  outputMap["done_percent"] = createRandomDouble(0.0, 100.0);
  outputMap["productivity_new"] = createRandomDouble(0.0, 1.0);
  outputMap["productivity_exp"] = createRandomDouble(1.0, 10.0);
  outputMap["d"] = createRandomDouble(0.0, 90.0);
  outputMap["ep"] = createRandomDouble(1.0, 30.0);
  outputMap["nprod"] = createRandomDouble(0.1, 1.0);
  outputMap["np"] = createRandomDouble(1.0, 30.0);
  outputMap["ts"] = createRandomDouble(1.0, 10.0);
  outputMap["to"] = createRandomDouble(1.0, 100.0);
  outputMap["r"] = createRandomDouble(100.0, 1000.0);
  outputMap["verbose"] = round(createRandomDouble(0.0, 1.0));
  
  return outputMap;
}

void outputArgs(unordered_map<string, double> outputMap) {
  bool verbose = false;
  if (outputMap["verbose"] >= 0.5)
    verbose = true;
  printf("{'pomposity': %.2f, 'learning_curve': %.2f, 'optimism': %.2f, 'atleast': %.2f, "
  	     "'done_percent': %.2f, 'productivity_new': %.2f, 'productivity_exp': %.2f, "
  	     "'d': %.2f, 'ep': %.2f, 'nprod': %.2f, 'np': %.2f, 'ts': %.2f, 'to': %.2f, "
  	     "'r': %.2f, 'verbose': ", outputMap["pomposity"], outputMap["learning_curve"],
  	   outputMap["optimism"], outputMap["atleast"], outputMap["done_percent"],
  	   outputMap["productivity_new"], outputMap["productivity_exp"], outputMap["d"],
  	   outputMap["ep"], outputMap["nprod"], outputMap["np"], outputMap["ts"],
  	   outputMap["to"], outputMap["r"]);
  if (verbose) cout << "True}";
  else cout <<"False}";
}

int main(int argc, char* argv[]) {
  srand(seed);
  inputArgs(argc, argv);
  unordered_map<string, double> outputMap = createMap();
  outputArgs(outputMap);
}
