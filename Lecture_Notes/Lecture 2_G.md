# Computing Cost of VM and Cloud
* Keeping 3 servers on a single machine will increase utilization and provides flexibility.
* Availability is another important parameter for virtualizing. The services should be available all the time and it is measured using uptime and downtime.

# Virtual Machines
* Virtualize whole system environment including OS and applications
* Virtual machine monitor or hypervisor is a software layer that provides virtualization support and manages multiple virtual machines.
* Charateristics of VMM
    * Equivalence - Program should run similar to what it runs in native mode except for performance.
    * Efficiency - Programs running in a VM should show only minor decrease in speed.
    * Resource Control - VMM should have complete control of system resources because giving one guest OS the control (out of multiple guest OS) is not good.
* If a machine has these properties, then it can be virtualized.
* **Theorem**
    * An architecture can be virtualized if sensitive instructions (instructions that change system state) are a subset of privileged instructions (can be only executed in kernel mode).
    * Regular instructions can be directly run.