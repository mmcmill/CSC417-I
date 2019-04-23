#include <iostream>
#include <string>
#include <cstdlib>
#include <math.h>
#include <unordered_map>
#include <iomanip>

using namespace std;

#define PRINT_ERROR_REPORT cout << "Arguments must either be -n [int] , -s [int] , or both" << endl;
#define SUCCESS return EXIT_SUCCESS;
#define FAILURE return EXIT_FAILURE;
#define CREATE_MAP_ENTRY(a,b,c) outputMap[a] = createRandomDouble(b, c);
#define INIT_SEED(a) seed = stoi(argv[a]);
#define INIT_NUM_REPEATS(a) numRepeats = stoi(argv[a]);
#define MAIN_ARGS int argc, char* argv[]
#define CATCH catch (exception e)
#define COMP_S(a) strcmp(argv[a], "-s") == 0
#define COMP_N(a) strcmp(argv[a], "-n") == 0
#define ARGS_COMP(a) argc == a
#define NR_CHECK numRepeats <= 0
#define ZERO_TO_ONE double(rand()) / (double(RAND_MAX))
#define CHECK_BOUNDS(a,b,c) ( a < b || a > c)
#define SET_VERBOSE if (outputMap["verbose"] >= 0.5) verbose = true;

int numRepeats = 1;
int seed = 1;

int inputArgs(MAIN_ARGS) {
  if (ARGS_COMP(1))
    SUCCESS
  else if (ARGS_COMP(3)) {
    if (COMP_N(1)) {
      try {
        INIT_NUM_REPEATS(2)
        if (NR_CHECK) {
          PRINT_ERROR_REPORT
          FAILURE
        }
      }
      CATCH {
        PRINT_ERROR_REPORT
        FAILURE
      }
    }
    else if (COMP_S(1)) {
      try {
        INIT_SEED(2)
      }
      CATCH {
        PRINT_ERROR_REPORT
        FAILURE
      }
    }
    else {
      PRINT_ERROR_REPORT
      FAILURE
    }
  }
  else if (ARGS_COMP(5)) {
    if (COMP_N(1)) {
      try {
        INIT_NUM_REPEATS(2)
        if (NR_CHECK) {
          PRINT_ERROR_REPORT
          FAILURE
        }
      }
      CATCH {
        PRINT_ERROR_REPORT
        FAILURE
      }
    }
    else if (COMP_S(1)) {
      try {
        INIT_SEED(2)
      }
      CATCH {
        PRINT_ERROR_REPORT
        FAILURE
      }
    }
    else {
      PRINT_ERROR_REPORT
      FAILURE
    }
    if (COMP_N(3)) {
      try {
        INIT_NUM_REPEATS(4)
        if (NR_CHECK){
          PRINT_ERROR_REPORT
          FAILURE
        } 
      }
      CATCH {
        PRINT_ERROR_REPORT
        FAILURE
      }
    }
    else if (COMP_S(3)) {
      try {
        INIT_SEED(4)
      }
      CATCH {
        PRINT_ERROR_REPORT
        FAILURE
      }
    }
    else {
      PRINT_ERROR_REPORT
      FAILURE
    }
  }
  else {
    PRINT_ERROR_REPORT
    FAILURE
  }
  SUCCESS
}

double doubleRand() {
  double val = ZERO_TO_ONE;
  if (CHECK_BOUNDS(val, 0, 1))
    return doubleRand();
  return val;
}


double createRandomDouble(double min, double max) {
  return roundf(((max - min) * doubleRand() + min) * 100) / 100;
}

unordered_map<string, double> createMap() {

  unordered_map<string, double> outputMap;

  CREATE_MAP_ENTRY("pomposity", double(0.0), double(1.0))
  CREATE_MAP_ENTRY("learning_curve", 1.0, 100.0)
  CREATE_MAP_ENTRY("optimism", 0.1, 10.0)
  CREATE_MAP_ENTRY("atleast", 0.0, 100.0)
  CREATE_MAP_ENTRY("done_percent", 0.0, 100.0)
  CREATE_MAP_ENTRY("productivity_new", 0.0, 1.0)
  CREATE_MAP_ENTRY("productivity_exp", 1.0, 10.0)
  CREATE_MAP_ENTRY("d", 0.0, 90.0)
  CREATE_MAP_ENTRY("ep", 1.0, 30.0)
  CREATE_MAP_ENTRY("nprod", 0.1, 1.0)
  CREATE_MAP_ENTRY("np", 1.0, 30.0)
  CREATE_MAP_ENTRY("ts", 1.0, 10.0)
  CREATE_MAP_ENTRY("to", 1.0, 100.0)
  CREATE_MAP_ENTRY("r", 100.0, 1000.0)
  CREATE_MAP_ENTRY("verbose", 0.0, 1.0)
  
  return outputMap;
}

void outputArgs(unordered_map<string, double> outputMap) {
  bool verbose = false;
  SET_VERBOSE
  printf("{'pomposity': %.2f, 'learning_curve': %.2f, 'optimism': %.2f, 'atleast': %.2f, "
  	     "'done_percent': %.2f, 'productivity_new': %.2f, 'productivity_exp': %.2f, "
  	     "'d': %.2f, 'ep': %.2f, 'nprod': %.2f, 'np': %.2f, 'ts': %.2f, 'to': %.2f, "
  	     "'r': %.2f, 'verbose': ", outputMap["pomposity"], outputMap["learning_curve"],
  	   outputMap["optimism"], outputMap["atleast"], outputMap["done_percent"],
  	   outputMap["productivity_new"], outputMap["productivity_exp"], outputMap["d"],
  	   outputMap["ep"], outputMap["nprod"], outputMap["np"], outputMap["ts"],
  	   outputMap["to"], outputMap["r"]);
  if (verbose)
    cout << "True}" << endl;
  else 
    cout <<"False}" << endl;
}

int main(MAIN_ARGS) {
  //Quarantine - input
  if (inputArgs(argc, argv) == 1)
    return 1; 
  srand(seed);
  for (int i = 0; i < numRepeats; i++){
    unordered_map<string, double> outputMap = createMap();
  //Quarantine - output
  outputArgs(outputMap);
  }
  
}
