namespace AuditRequests.Core.Data.Entity
{
    public class RequestAudit
    {
        public Guid Id { get; set; }
        public DateTimeOffset requestedAt { get; set; }
        public string Method { get; set; }
        public string Url { get; set; }
        public string IP { get; set; }
        public string UserAgent { get; set; }

        public string Body { get; set; }

        // Headers serializados como JSON ou outro formato legível
        public Dictionary<string, string> Headers { get; set; }

        // Dados adicionais, se quiser expandir depois
        public string TraceId { get; set; }

    }
}
