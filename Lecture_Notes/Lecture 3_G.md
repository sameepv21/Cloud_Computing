# Priviledge ring architecture
* 4 rings viz ring 0 - 3
* Ring 0 is called hypervisor and has the most priviledges
* Ring 3 is least priviledge and run applications
* Ring 2 is unused rings
* Ring 1 runs VMs.
* Root user doesn't mean operating in ring 0. Kernel mode with root mode is ring 0.
* Problem was that architectures/hardware was designed to handle only one VM.
* Hence there are certain sensitive instructions that are not priviledged instructions.
* For those instructions, solution is that we would "emulate" them which gives an idea that instructions are run but actually on hardware we are not running these instructions.
* Issue with one this is time complexity increases because software implementation consumes more time than hardware implementation.

# Recursive Virtualization
* Creating a VMM inside one of the VMs.
* The requirement is that it would be helpful in sandboxing, malware analysis, testing etc.