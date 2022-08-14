#include <rpc/rpc.h>
#include "print.h"
#include <stdio.h>
char s;
int total;
void * q;

char * print_1(void * b,
        CLIENT *client) {

     s='k';  
printf("\nsending from function print_1 char = %c\n", s);
  return(&s);
}
char *  print_1_svc(void * b,struct svc_req *svc) {
  CLIENT *client;
 printf("\nin print_1_svc\n");
  return(print_1(q,client));
}

int * total_1(int * in,
        CLIENT *client) {
        total = (*in) + 5;
	printf("\nsending from function total_1 = %d\n", total);
        return(&total);
}
int *  total_1_svc(int * in,struct svc_req *svc) {
  CLIENT *client;
	 printf("\nin total_1_svc\n");
	 return(total_1(in,client));
}

