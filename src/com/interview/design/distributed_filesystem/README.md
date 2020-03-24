# Distributed File system

## File structure

Save a file in a file system

- Metadata

We may to query it very often. name, create_time, size, chunk indexes

- Chunks

Store the content of the file, each chunk can be 1K or 64MB, depending on the file size; It could waste space for small files

## Master Slave

- Master: metadata
- Slave: chunks

## Upload

1. The client cuts file into chunks and then uploads to the server. If any transmission failed, we just need to retry one chunk
1. Client sends a request to master server with chunk index 1
1. Master response the chunk server's URL
1. Client uploads all chunks to the assigned server
1. When the uploading is done, chunk server notifies the master server that all work is done

## Modify

- We could upload a new copy and delete the old one

## Read

1. Client sends request to the Master server to get the chunk list and location
1. Client sends request to the Slave server to download chunks