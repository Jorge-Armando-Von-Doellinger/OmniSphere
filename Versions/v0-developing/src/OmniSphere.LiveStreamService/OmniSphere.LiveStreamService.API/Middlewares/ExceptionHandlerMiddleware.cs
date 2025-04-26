using System.Text.Json;
using OmniSphere.LiveStreamService.API.Exceptions;

public class ExceptionHandlerMiddleware
{
    private readonly ILogger<ExceptionHandlerMiddleware> _logger;
    private readonly RequestDelegate _next;

    public ExceptionHandlerMiddleware(RequestDelegate next, ILogger<ExceptionHandlerMiddleware> logger)
    {
        _next = next;
        _logger = logger;
    }

    public async Task InvokeAsync(HttpContext context)
    {
        try
        {
            await _next(context);
        }
        catch (RequestHeaderNotFounded ex)
        {
            _logger.LogWarning(ex, "Missing header");
            await WriteResponse(context, 400, ex.Message);
        }
        catch (RequestFormNotFound ex)
        {
            _logger.LogWarning(ex, "Missing form field");
            await WriteResponse(context, 400, ex.Message);
        }
        catch (UnauthorizedAccessException ex)
        {
            _logger.LogWarning(ex, ex.Message);
            await WriteResponse(context, 401, ex.Message);
        }
        catch (Exception ex) // Here is an internal errors, how connection refused by the database
        {
            _logger.LogError(ex, "Unexpected error");
            await WriteResponse(context, 500, "Internal Server Error. Please, notify the TI team.");
        }
    }

    private async Task WriteResponse(HttpContext context, int statusCode, string message)
    {
        context.Response.StatusCode = statusCode;
        context.Response.ContentType = "application/json";

        var response = new
        {
            error = message,
            status = statusCode,
            timestamp = DateTime.UtcNow
        };

        await context.Response.WriteAsync(JsonSerializer.Serialize(response));
    }
}