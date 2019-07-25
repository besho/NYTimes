# Nytimes

App divided into two packages:-

1. ui:- responsible for handling App screens using MVP design pattern. Each screen components added on single package with defined name.
2. engine:- responsible for handling services connections through ServiceFactory using Okhttp3 anf rxJava. 
   Also all static parameters mentioned on utils package in Constants class.
   Constants.class contains:-
                              - Server Base URL & api-key
                              - List of APIS IDs an names
                              - API calls timeout.

