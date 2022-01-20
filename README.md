
# Problem description

Start the project and run the Gradle task `test`.

The `patchExample` test fails with a 400 bad request.

Uncomment the `domainObjectReaderCustomizer` in `JacksonConfig` to replace the
`DomainObjectReader` with `CustomDomainObjectReader` and run the tests again.
The test passes now. See the comments in `CustomDomainObjectReader`.
