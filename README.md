# PDF Unlocker
## How to use
```
Usage: ./pdf-unlocker [OPTIONS]

Options:
  -s, --src PATH   The source file to unlock.
  -d, --dest PATH  Where the file will be saved, once unlocked.
  -p, --pwd TEXT   The password to unlock the file.
  -i, --ip         Should the file be unlocked in place.
  -h, --help       Show this message and exit
```

The source file, and the password are the only required arguments.

### Examples:
#### Will unlock the file in place
```
./pdf-unlocker --src locked-file.pdf --pwd secretPassword --ip
```

#### Will unlock the file and save it to `unlocked-file.pdf`
```
./pdf-unlocker --src locked-file.pdf --dest unlocked-file.pdf --pwd secretPassword
```

#### Will unlock the file and save it to `locked-file_unlocked.pdf`
```
./pdf-unlocker --src locked-file.pdf --pwd secretPassword
```
