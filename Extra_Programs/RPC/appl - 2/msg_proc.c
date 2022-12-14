/*
 * msg_proc.c: implementation of the remote 
 *procedure "printmessage"
 */
#include <stdio.h> 
#include <rpc/rpc.h>   /* always needed  */
#include "msg.h"       /* msg.h will be generated by rpcgen */

/*
 * Remote version of "printmessage"
 */ int *
int * printmessage_1(char **msg, struct svc_req *req)
{ 
    static int result;  /* must be static! */
    FILE *f;
    f = fopen("/dev/console", "w");
    if (f == NULL) { 
        result = 0;
        return (&result);
    }
    fprintf(f, "%s\en", *msg);
    fclose(f);
    result = 1;
    return (&result);
}