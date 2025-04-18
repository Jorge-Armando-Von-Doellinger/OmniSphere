# Etapa 1: Build
FROM mcr.microsoft.com/dotnet/sdk:9.0 AS build
WORKDIR /src

# Copiar o arquivo de solução e os projetos
COPY OmniSphere.LiveStreamService.sln .
COPY OmniSphere.LiveStreamService.API/OmniSphere.LiveStreamService.API.csproj OmniSphere.LiveStreamService.API/
COPY OmniSphere.LiveStreamService.Application/OmniSphere.LiveStreamService.Application.csproj OmniSphere.LiveStreamService.Application/
COPY OmniSphere.LiveStreamService.Core/OmniSphere.LiveStreamService.Core.csproj OmniSphere.LiveStreamService.Core/
COPY OmniSphere.LiveStreamService.Infrastructure/OmniSphere.LiveStreamService.Infrastructure.csproj OmniSphere.LiveStreamService.Infrastructure/
# Restaurar dependências
RUN dotnet restore

# Copiar o restante dos arquivos
COPY OmniSphere.LiveStreamService.API/ OmniSphere.LiveStreamService.API/
COPY OmniSphere.LiveStreamService.Application/ OmniSphere.LiveStreamService.Application/
COPY OmniSphere.LiveStreamService.Core/ OmniSphere.LiveStreamService.Core/
COPY OmniSphere.LiveStreamService.Infrastructure/ OmniSphere.LiveStreamService.Infrastructure/




# Compilar e publicar a API
WORKDIR /src/OmniSphere.LiveStreamService.API
RUN dotnet publish -c Release -o /app/publish --no-restore

# Etapa 2: Runtime
FROM mcr.microsoft.com/dotnet/aspnet:9.0 AS final
WORKDIR /app

COPY OmniSphere.LiveStreamService.API/application-prod.yaml /app/application-prod.yaml

# Instalar FFmpeg
RUN apt-get update && \
    apt-get install -y ffmpeg && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copiar os binários publicados
COPY --from=build /app/publish .

# Definir variável de ambiente (opcional, ajuste conforme necessário)
ENV ASPNETCORE_ENVIRONMENT=Developing

# Expor a porta da API (ajuste conforme sua configuração)
EXPOSE 5000

# Definir o ponto de entrada
ENTRYPOINT ["dotnet", "OmniSphere.LiveStreamService.API.dll"]