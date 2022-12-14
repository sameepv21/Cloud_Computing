Listing 2.

/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _AVG_H_RPCGEN
#define _AVG_H_RPCGEN

#include <rpc/rpc.h>

#define MAXAVGSIZE 200

struct input_data {
	struct {
		u_int input_data_len;
		double *input_data_val;
	} input_data;
};
typedef struct input_data input_data;
#ifdef __cplusplus 
extern "C" bool_t xdr_input_data(XDR *, 
	input_data*);
#elif __STDC__ 
extern  bool_t xdr_input_data(XDR *, input_data*);
#else /* Old Style C */ 
bool_t xdr_input_data();
#endif /* Old Style C */

#ifdef __cplusplus
extern "C" bool_t xdr_input_data(XDR *, input_data*);
#elif __STDC__
extern  bool_t xdr_input_data(XDR *, input_data*);
#else /* Old Style C */
bool_t xdr_input_data();
#endif /* Old Style C */


#define AVERAGEPROG ((u_long)22855)
#define AVERAGEVERS ((u_long)1)

#ifdef __cplusplus
#define AVERAGE ((u_long)1)
extern "C" double * average_1(input_data *, CLIENT *);
extern "C" double * average_1_svc(input_data *, 
	struct svc_req *);

#elif __STDC__
#define AVERAGE ((u_long)1)
extern  double * average_1(input_data *, 
	CLIENT *);
extern  double * average_1_svc(input_data *, 
	struct svc_req *);

#else /* Old Style C */
#define AVERAGE ((u_long)1)
extern  double * average_1();
extern  double * average_1_svc();
#endif /* Old Style C */

#endif /* !_AVG_H_RPCGEN */

