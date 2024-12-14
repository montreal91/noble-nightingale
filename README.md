# Noble Nightingale

Market Simulation Project.

This is also my visit card on Distributed Systems, Clean Architecture, Domain-Driven Design and Microservices.<br>
Also maybe a platform for market analysis.

### PTC.3.A Model
**PTC.3.A Model** stands for Production-Trade Cycle with 3 resource types 
and an arbitrary number of manufacturers Model

Try to employ TDD.<br>
**Todo:**

1. [x] Get current context. It can be 
   1. [x] Production context when manufacturers gather
      information to decide how much product they 
      need to produce and select production price.
   2. [x] Trading context. In this context manufacturers 
      choose to buy what from whom and trying to 
      make the best deals possible.
2. [x] Actions
   1. [x] Produce Resource
   2. [x] Set Selling Price
   3. [x] Finish Production
   4. [x] Make a Deal / Buy Resource
   5. [x] Finish Trading
3. [ ] Simulation cycle
4. [ ] Passive Stance market strategy <br>*(Agent does nothing to improve their positions)*
5. [ ] Simulation results
6. [ ] Philanthropist <br>*(Agent suffices demand with minimum production and gives it for free)*
7. [ ] Total resource demand <br>*(A manufacturer knows how many units of the resource they produce is required on the market)*
8. [ ] Naive market strategy <br>*(every market strategy should maximize agent's profit)*
9. [ ] A protocol, how to add more strategies
10. [ ] Infrastructure
    1. [ ] User authentication and authorization
    2. [ ] Persistence
    3. [ ] Registration process
    4. [ ] Administration
    5. [ ] Setup (initial conditions) management
    6. [ ] Setup validation
    7. [ ] Single player mode
    8. [ ] Asynchronous simulation
11. [ ] A protocol, how to add more models
