# Currency Gateway API

## Overview
The **Currency Gateway API** is a Spring Boot application that provides currency exchange data from Fixer.io. It supports fetching current and historical exchange rates through JSON and XML APIs. The service automatically fetches currency data every day at **4 AM**.

## Features
- **Scheduled Data Fetching**: Automatically retrieves and stores currency data from Fixer.io at **4 AM** every day.
- **JSON and XML Endpoints**: Supports multiple content types for flexible integration.
- **Request Logging**: Logs all requests to prevent duplication and for audit purposes.

## API Endpoints

### JSON API
- **Fetch Current Rate**  
  `POST /json_api/current`

- **Fetch Historical Data**  
  `POST /json_api/history`

### XML API
- **Fetch Current Rate**  
  `POST /xml_api/command`