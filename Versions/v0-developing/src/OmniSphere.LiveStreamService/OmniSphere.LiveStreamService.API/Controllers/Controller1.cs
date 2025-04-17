using Microsoft.AspNetCore.Mvc;

namespace OmniSphere.LiveStreamService.API.Controllers;

[ApiController]
[Route("/nothing")]
public class Controller1 : Controller
{
    [HttpGet]
    public string Index()
    {
        return "Batata";
    }
}