#include <stdio.h>
#include <time.h>

long bin_date(void) 
{
  return (long) time(NULL);
}

char *str_date(long t)
{
  return ctime((time_t *) &t);
}

int main(int argc, char **argv)
{
  long t;
  char *str;

  t = bin_date();
  str = str_date(t);

  puts(str);
  exit(0);
}  