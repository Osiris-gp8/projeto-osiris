import setuptools

setuptools.setup(
    name="pipeline",
    version='1.0',
    install_requirements=[
        "pandas==1.2.4",
        "numpy==1.20.2",
        "jupyter==1.0.0",
        "pytest==6.2.5",
        "requests==2.25.1",
        "SQLAlchemy==1.4.13",
        "mysql-connector-python==8.0.24",
        "pyodbc==4.0.32"
    ],
    packages=setuptools.find_packages()
)