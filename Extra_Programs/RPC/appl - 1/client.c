#include "print.h"
#include <stdlib.h>
void * q;
int out;
void printing_1( char* host, int argc, char *argv[])
{
	   CLIENT *clnt;
	   char *crec;
	   int  *irec;
           clnt = clnt_create(host, PRINTPROG,PRINTVERS, "tcp");
printf("created tcp with host...\n");
   if (clnt == NULL) {
      clnt_pcreateerror(host);
      exit(1);
   }
//out->a = 5;
//out->b = 10;
out = 6;
 printf("calling print_1...\n");
	printf("calling total_1..sending %d as input\n",out);
   crec = print_1(&q, clnt);
   irec = total_1(&out, clnt);
   if (crec == NULL) {
      clnt_perror(clnt, "call failed:");
   }
   clnt_destroy( clnt );
      printf("received char = %c\n",*crec);
      printf("received int  = %d\n",*irec);
}


main( int argc, char* argv[] )
{
   char *host;
   host = argv[1];
printf ("\n%s\n",host);
   printing_1( host, argc, argv);
}


