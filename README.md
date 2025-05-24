# Gremlin TinkerGraph Docker Environment

This project provides a Docker-based environment for running an Apache TinkerPop Gremlin Server with a TinkerGraph backend, pre-configured for development and testing. It also includes sample data seeding scripts and instructions for interacting with the graph using the Gremlin Console.

## Project Structure

- **Dockerfile.gremlin**
  Builds a custom Gremlin Server image using TinkerGraph as the backend and copies the configuration file.

- **docker-compose.yml**
  Defines the Gremlin Server service, network, and port mappings for easy startup and management.

- **conf/tinkergraph-empty.properties**
  Configuration file for TinkerGraph, specifying ID management and enabling example services.

- **scripts/seed.groovy**
  Groovy script to seed the graph with sample identity and address data. Can be loaded via the Gremlin Console.

## Getting Started

### 1. Build and Start the Gremlin Server

```sh
docker-compose up --build
```
This will build the custom Gremlin Server image and start the server on port `8182`.

#### - Stop the Gremlin Server
To gracefully stop the running Gremlin Server container, use:

```sh
docker-compose down
```

#### - Restart the Gremlin Server (without rebuilding)
To start the server again without rebuilding the image, run:
```sh
docker-compose up
```

#### - (Optional) Start the Gremlin Server in Detached Mode
To run the Gremlin Server in the background (detached mode), use:
```sh
docker-compose up -d

```

### 2. Start the Gremlin Console

Open a new terminal and run:

```sh
docker run -it --rm --network host -v ./scripts:/opt/gremlin-console/scripts tinkerpop/gremlin-console:latest
```

This command starts the Gremlin Console with your local `scripts` directory mounted for easy script loading. It will map the `scripts` folder to the `scripts` folder in the gremlin-console container, this way, any new script created will be available in the container.

### 3. Connect the Gremlin Console to the Remote Server

In the Gremlin Console, connect to the running Gremlin Server:

```groovy
:remote connect tinkerpop.server conf/remote.yaml
:remote console
```

If you do not have a `conf/remote.yaml`, you can connect directly:

```groovy
:remote connect tinkerpop.server conf/remote.yaml
```

Or, for a simple connection:

```groovy
:remote connect tinkerpop.server conf/remote.yaml
```

Make sure your `remote.yaml` points to `localhost:8182`.

### 4. Load and Run the Seed Script

Once connected, load the seed script:

```groovy
:load scripts/seed.groovy
```

### 5. Validate the Graph Data
After loading the seed script, you can run the following queries in the Gremlin Console to validate that the graph has been populated:
```groovy
 // List all vertices
 g.V().valueMap(true)

 // Count all edges
 g.E().count()
```

This will populate the graph with sample data.

## Notes

- The Gremlin Server runs on port `8182` by default.
- The sample data includes identities and addresses, with relationships between them.
- You can modify `scripts/seed.groovy` to customize the initial data.
- You can create more  `.groovy` scripts to load in the gremlin console, just follow step #4 to load the newly created scripts.
