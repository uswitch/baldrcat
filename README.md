# baldrcat

<img alt="baldrcat" height="400px" src="http://www.clipartbest.com/cliparts/dcr/Me5/dcrMe5qc9.png"/>

Tool to help debug data infrastructure. Can cat the records contained in Baldr files on S3.

## Usage:

    $ ./baldrcat --access-key XXXX --secret-key XXXX s3://bucket/object1 s3://bucket/object2

Note: AWS Access and Secret keys will default to the `AWS_ACCESS_KEY` and `AWS_SECRET_KEY` environment variables.

`baldrcat` also assumes that the record payload is a set of bytes representing a string.
