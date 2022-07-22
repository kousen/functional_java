package http;

import java.util.List;

public record AstroResponse(int number,
                            String message,
                            List<Assignment> people) {}
